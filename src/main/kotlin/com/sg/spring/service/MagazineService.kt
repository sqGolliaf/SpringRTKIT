package com.sg.spring.service

import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.domain.UpdateMagazineRequest

interface MagazineService {
    fun save(request: CreateMagazineRequest): MagazineResponse?

    fun getById(id: Int): MagazineResponse?

    fun getAll(): MagazinesResponse

    fun updateById(id: Int, request: UpdateMagazineRequest): MagazineResponse?

    fun deleteById(id: Int): MagazineResponse?
}