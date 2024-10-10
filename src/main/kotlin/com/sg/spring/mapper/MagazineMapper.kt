package com.sg.spring.mapper

import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.UpdateMagazineRequest
import com.sg.spring.model.Magazine
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MagazineMapper {
    fun mapToModel(request: CreateMagazineRequest): Magazine = Magazine(
        name = request.name, text = request.text
    )

    fun mapToModel(request: UpdateMagazineRequest): Magazine = Magazine(
        text = request.text
    )

    fun mapToResp(model: Magazine?): MagazineResponse = MagazineResponse(
        id = model?.id, name = model?.name, text = model?.text
    )
}

fun MagazineResponse?.toResponseEntity(): ResponseEntity<MagazineResponse> =
    this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()