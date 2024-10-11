package com.sg.spring.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.service.MagazineService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

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
    fun getAll() {
        val resp = getRespMagazines()

        every { magazineService.getAll() } returns resp

        mvc.get(URL_MAGAZINE) {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

        verify(exactly=1) { magazineService.getAll() === resp}

    }

    private fun getReqMagazine(): CreateMagazineRequest =
        CreateMagazineRequest("testName", "testText")

    private fun getRespMagazine(): MagazineResponse =
        MagazineResponse(1, "testName", "testText")

    private fun getRespMagazines(): MagazinesResponse =
        MagazinesResponse(listOf(
            MagazineResponse(0, "War And Peace", "asd")
        ))

    companion object {
        const val URL_MAGAZINE = "/api/v1/magazine"
    }
}