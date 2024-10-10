package com.sg.spring.dao

import com.sg.spring.model.Magazine

interface MagazineDao {
    fun save(magazine: Magazine): Magazine

    fun getById(id: Int): Magazine?

    fun getAll(): List<Magazine?>

    fun updateById(id: Int, magazine: Magazine): Magazine?

    fun deleteById(id: Int): Magazine?
}