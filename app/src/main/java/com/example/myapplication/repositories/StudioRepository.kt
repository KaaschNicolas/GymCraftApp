package com.example.testapp.repositories

import com.example.testapp.daos.StudioDao
import com.example.testapp.models.Studio
import javax.inject.Inject

class StudioRepository
@Inject constructor(
    private val studioDao: StudioDao
){
    suspend fun save(studio: Studio) = studioDao.save(studio)

    suspend fun delete(studio: Studio) = studioDao.save(studio)

    suspend fun getAll() = studioDao.getAll()

    suspend fun getOneById(id: Int) = studioDao.getOneById(id)
}