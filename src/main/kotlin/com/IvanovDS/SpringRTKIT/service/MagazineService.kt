package com.IvanovDS.SpringRTKIT.service

import com.IvanovDS.SpringRTKIT.model.Magazine

interface MagazineService {

    fun getById(id: Int): Magazine

    fun getAll(): List<Magazine>

    fun save(magazine: Magazine): Magazine

    fun deleteById(id: Int): Magazine

    fun updateById(id: Int, magazine: Magazine): Magazine
}