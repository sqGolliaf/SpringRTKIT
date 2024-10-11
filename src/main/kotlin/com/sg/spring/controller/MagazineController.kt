package com.sg.spring.controller

import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.domain.UpdateMagazineRequest
import com.sg.spring.mapper.toResponseEntity
import com.sg.spring.service.MagazineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/magazine")
class MagazineController(private val service: MagazineService) {

    @PostMapping
    fun save(@RequestBody request: CreateMagazineRequest): ResponseEntity<MagazineResponse> {
        val savedMagazine = service.save(request)
        return ResponseEntity.created(URI("/${savedMagazine?.id}")).body(savedMagazine)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable(name = "id") id: Int): ResponseEntity<MagazineResponse> =
        service.getById(id).toResponseEntity()

    @GetMapping
    fun findAll(): ResponseEntity<MagazinesResponse> = ResponseEntity.ok(service.getAll())

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable(name = "id") id: Int, @RequestBody magazine: UpdateMagazineRequest
    ): ResponseEntity<MagazineResponse> = service.updateById(id, magazine).toResponseEntity()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(name = "id") id: Int): ResponseEntity<*> = service.deleteById(id).toResponseEntity()
}
