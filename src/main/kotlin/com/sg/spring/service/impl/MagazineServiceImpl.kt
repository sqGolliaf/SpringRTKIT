package com.sg.spring.service.impl

import com.sg.spring.dao.MagazineDao
import com.sg.spring.domain.CreateMagazineRequest
import com.sg.spring.domain.MagazineResponse
import com.sg.spring.domain.MagazinesResponse
import com.sg.spring.domain.UpdateMagazineRequest
import com.sg.spring.mapper.MagazineMapper
import com.sg.spring.service.MagazineService
import org.springframework.stereotype.Service

@Service
class MagazineServiceImpl(
    private val magazineDao: MagazineDao, private val mapper: MagazineMapper
) : MagazineService {

    override fun save(request: CreateMagazineRequest): MagazineResponse? {
        val magazine = mapper.mapToModel(request)
        val entity = magazineDao.save(magazine)
        return mapper.mapToResp(entity)
    }

    override fun getById(id: Int): MagazineResponse? = mapper.mapToResp(magazineDao.getById(id))

    override fun getAll(): MagazinesResponse {
        val entityList = magazineDao.getAll()
        return MagazinesResponse(entityList.map { mapper.mapToResp(it) })
    }

    override fun updateById(id: Int, request: UpdateMagazineRequest): MagazineResponse? {
        val entity = mapper.mapToModel(request)
        val magazine = magazineDao.updateById(id, entity)
        return mapper.mapToResp(magazine)
    }

    override fun deleteById(id: Int): MagazineResponse? = mapper.mapToResp(magazineDao.deleteById(id))
}