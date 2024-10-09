package com.IvanovDS.SpringRTKIT.controller

import com.IvanovDS.SpringRTKIT.model.Magazine
import com.IvanovDS.SpringRTKIT.service.MagazineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/magazine")
class MagazineController(private val service: MagazineService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable(name = "id") id: Int): ResponseEntity<Magazine> =
        service.getById(id).toResponseEntity()

    @GetMapping
    fun findAll(): ResponseEntity<List<Magazine>> = ResponseEntity.ok(service.getAll())

    @PostMapping
    fun save(@RequestBody magazine: Magazine): ResponseEntity<Magazine> {
        val savedMagazine = service.save(magazine)
        return ResponseEntity.created(URI("/${savedMagazine.id}")).body(savedMagazine)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(name = "id") id: Int): ResponseEntity<*> =
        service.deleteById(id).toResponseEntity()

    @PutMapping("/{id}")
    fun updateById(@PathVariable(name = "id") id: Int,
                   @RequestBody magazine: Magazine): ResponseEntity<Magazine> =
        service.updateById(id, magazine).toResponseEntity()

    private fun Magazine?.toResponseEntity(): ResponseEntity<Magazine> = 
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}
