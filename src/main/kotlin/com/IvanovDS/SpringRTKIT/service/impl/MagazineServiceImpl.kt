package com.IvanovDS.SpringRTKIT.service.impl

import com.IvanovDS.SpringRTKIT.model.Magazine
import com.IvanovDS.SpringRTKIT.service.MagazineService
import org.springframework.stereotype.Service

@Service
class MagazineServiceImpl : MagazineService {

    val magazineList = mutableListOf(
        Magazine(0, "War and Peace", "asd..."), Magazine(1, "Martin Eden", "zxc...")
    )

    override fun getById(id: Int): Magazine = magazineList[id]

    override fun getAll(): List<Magazine> = magazineList

    override fun save(magazine: Magazine): Magazine {
        magazineList.add(magazine)
        return magazine
    }

    override fun deleteById(id: Int): Magazine {
        return magazineList.removeAt(id)
    }

    override fun updateById(id: Int, magazine: Magazine): Magazine {
        deleteById(id)
        magazineList.add(id, magazine)
        return magazine
    }
}