package com.example.testapp.repositories

import com.example.testapp.daos.CustomerCourseMappingDao
import com.example.testapp.models.CustomerCourseMapping
import javax.inject.Inject

class CustomerCourseRepository
@Inject constructor(
    private val customerCourseMappingDao: CustomerCourseMappingDao
) {
    suspend fun save(customerCourseMapping: CustomerCourseMapping)
        = customerCourseMappingDao.insert(customerCourseMapping)

    suspend fun delete(customerCourseMapping: CustomerCourseMapping)
        = customerCourseMappingDao.delete(customerCourseMapping)

    suspend fun getAll() = customerCourseMappingDao.getAll()

    suspend fun getMappingsByCourseId(id: Int) = customerCourseMappingDao.getMappingsByCourseId(id)

    suspend fun getMappingsByCustomerId(id: Int) = customerCourseMappingDao.getMappingsByCustomerId(id)
}
