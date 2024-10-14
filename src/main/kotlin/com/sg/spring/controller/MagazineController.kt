package com.sg.spring.controller

import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.domain.UpdateMagazineRequest
import com.sg.spring.service.MagazineService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/magazine")
class MagazineController(private val service: MagazineService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateMagazineRequest): MagazineResponse? = service.save(request)

    @GetMapping("/{id}")
    fun findById(@PathVariable(name = "id") id: Int): MagazineResponse? = service.getById(id)

    @GetMapping
    fun findAll(): MagazinesResponse = service.getAll()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateById(
        @PathVariable(name = "id") id: Int, @RequestBody magazine: UpdateMagazineRequest
    ): MagazineResponse? = service.updateById(id, magazine)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteById(@PathVariable(name = "id") id: Int) = service.deleteById(id)
}
