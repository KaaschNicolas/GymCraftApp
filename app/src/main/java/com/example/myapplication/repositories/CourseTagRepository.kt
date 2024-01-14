package com.example.myapplication.repositories

import com.example.myapplication.daos.CourseTagMappingDao
import com.example.myapplication.models.CourseTagMapping
import javax.inject.Inject

class CourseTagRepository
@Inject constructor(
    private val courseTagMappingDao: CourseTagMappingDao
) {
    fun save(courseTagMapping: CourseTagMapping)
            = courseTagMappingDao.insert(courseTagMapping)

    fun delete(courseTagMapping: CourseTagMapping)
            = courseTagMappingDao.delete(courseTagMapping)

    fun getAll() = courseTagMappingDao.getAll()

    fun getMappingsByCourseId(id :Int) = courseTagMappingDao.getMappingsByCourseId(id)

    fun getMappingsByTagId(id: Int) = courseTagMappingDao.getMappingsByTagId(id)
}
