package com.example.myapplication.repositories

import com.example.myapplication.daos.CustomerCourseMappingDao
import com.example.myapplication.models.CustomerCourseMapping
import javax.inject.Inject

class CustomerCourseRepository
@Inject constructor(
    private val customerCourseMappingDao: CustomerCourseMappingDao
) {
    fun save(customerCourseMapping: CustomerCourseMapping)
        = customerCourseMappingDao.insert(customerCourseMapping)

    fun delete(customerCourseMapping: CustomerCourseMapping)
        = customerCourseMappingDao.delete(customerCourseMapping)

    fun getAll() = customerCourseMappingDao.getAll()

    fun getMappingsByCourseId(id: Int) = customerCourseMappingDao.getMappingsByCourseId(id)

    fun getMappingsByCustomerId(id: Int) = customerCourseMappingDao.getMappingsByCustomerId(id)
}
