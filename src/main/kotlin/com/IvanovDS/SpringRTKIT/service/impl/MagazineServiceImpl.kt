package com.IvanovDS.SpringRTKIT.service.impl

import com.IvanovDS.SpringRTKIT.model.Magazine
import com.IvanovDS.SpringRTKIT.service.MagazineService
import org.springframework.stereotype.Service

@Service
class MagazineServiceImpl : MagazineService {

    val magazineList = mutableListOf(
        Magazine("War and Peace"), Magazine("Martin Eden")
    )

    override fun getById(id: Int): Magazine = magazineList[id]

    override fun getAll(): List<Magazine> = magazineList

    override fun save(magazine: Magazine) {
        magazineList.add(magazine)
    }

    override fun delete(magazine: Magazine) {
        magazineList.removeAll {
            it == magazine
        }
    }

    override fun deleteById(id: Int) {
        magazineList.removeAt(id)
    }

    override fun updateById(id: Int, magazine: Magazine) {
        deleteById(id)
        magazineList.add(id, magazine)
    }
}