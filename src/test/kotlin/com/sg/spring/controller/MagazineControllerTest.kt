package com.sg.spring.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.domain.UpdateMagazineRequest
import com.sg.spring.service.MagazineService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@DisplayName("Тест контроллера")
@WebMvcTest(MagazineController::class)
internal class MagazineControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper
) {
    @MockkBean
    lateinit var magazineService: MagazineService

    @Test
    fun create_withCreated() {
        val req = getReqMagazine()
        val resp = getRespMagazine()

        every { magazineService.save(any()) } returns resp

        mvc.post(URL_MAGAZINE) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(req)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }

        verify(exactly=1) { magazineService.save(withArg { req === it})}
    }

    @Test
    fun getAll_withOk() {
        val resp = getRespMagazines()

        every { magazineService.getAll() } returns resp

        mvc.get(URL_MAGAZINE) {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

        verify(exactly=1) { magazineService.getAll() === resp}

    }

    @Test
    fun getByID_withOk() {
        val resp = getRespMagazines().magazines?.get(ID_BOOK)

        every { magazineService.getById(ID_BOOK) } returns resp

        mvc.get("${URL_MAGAZINE}/${ID_BOOK}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

        verify(exactly = 1) { magazineService.getById(ID_BOOK) === resp }
    }

    @Test
    fun deleteByID_withOk() {
        val resp = getRespMagazines().magazines?.get(ID_BOOK)

        every { magazineService.deleteById(ID_BOOK) } returns resp

        mvc.delete("${URL_MAGAZINE}/${ID_BOOK}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

        verify(exactly = 1) { magazineService.deleteById(ID_BOOK) === resp}
    }

    @Test
    fun updateByID_withContent() {
        val resp = getRespMagazineById()
        val req = updateReqMagazine()

        every { magazineService.updateById(ID_BOOK, any()) } returns resp

        mvc.put("${URL_MAGAZINE}/${ID_BOOK}") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(req)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }

        verify(exactly = 1) { magazineService.updateById(ID_BOOK, req) === resp }
    }

    private fun getReqMagazine(): CreateMagazineRequest =
        CreateMagazineRequest("testName", "testText")

    private fun getRespMagazine(): MagazineResponse =
        MagazineResponse(1, "testName", "testText")

    private fun getRespMagazineById(): MagazineResponse =
        MagazineResponse(0, "testName", "testText")

    private fun updateReqMagazine(): UpdateMagazineRequest =
        UpdateMagazineRequest("testName")

    private fun getRespMagazines(): MagazinesResponse =
        MagazinesResponse(listOf(
            MagazineResponse(0, "War And Peace", "asd")
        ))

    companion object {
        const val URL_MAGAZINE = "/api/v1/magazine"
        const val ID_BOOK = 0
    }
}