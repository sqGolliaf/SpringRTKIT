package com.sg.spring.dao.impl

import com.sg.spring.dao.MagazineDao
import com.sg.spring.model.Magazine
import org.springframework.stereotype.Service

@Service
class MagazineDaoImpl(
    private val magazineMock: ArrayList<Magazine> = arrayListOf(
        Magazine(0, "War And Peace", "asd")
    )
) : MagazineDao {
    override fun save(magazine: Magazine): Magazine {
        val id = magazineMock.size + 1
        magazine.id = id
        magazineMock.add(magazine)

        return magazine
    }

    override fun getById(id: Int): Magazine? = magazineMock.find { it.id == id }

    override fun getAll(): List<Magazine?> = magazineMock

    override fun updateById(id: Int, magazine: Magazine): Magazine? {
        val result = magazineMock.find { it.id == id }
        magazineMock.remove(result)

        result?.text = magazine.text

        magazineMock.add(result?: Magazine())

        return result
    }

    override fun deleteById(id: Int): Magazine? {
        val magazine = magazineMock.find { it.id == id }
        magazineMock.remove(magazine)

        return magazine
    }
}