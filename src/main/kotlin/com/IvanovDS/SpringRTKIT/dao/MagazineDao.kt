package com.IvanovDS.SpringRTKIT.dao

import com.IvanovDS.SpringRTKIT.model.Magazine

interface MagazineDao {
    fun save(magazine: Magazine): Magazine

    fun getById(id: Int): Magazine?

    fun getAll(): List<Magazine?>

    fun updateById(id: Int, name: String): Magazine?

    fun deleteById(id: Int)
}