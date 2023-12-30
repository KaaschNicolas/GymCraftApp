package com.example.myapplication.repositories

import com.example.myapplication.daos.StudioDao
import com.example.myapplication.models.Studio
import javax.inject.Inject

class StudioRepository
@Inject constructor(
    private val studioDao: StudioDao
){
    fun save(studio: Studio) = studioDao.save(studio)

    fun delete(studio: Studio) = studioDao.save(studio)

    fun getAll() = studioDao.getAll()

    fun getOneById(id: Int) = studioDao.getOneById(id)
}