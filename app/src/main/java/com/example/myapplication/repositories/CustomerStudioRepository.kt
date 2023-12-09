package com.example.myapplication.repositories

import com.example.myapplication.daos.CustomerStudioMappingDao
import com.example.myapplication.models.CustomerStudioMapping
import javax.inject.Inject

class CustomerStudioRepository
@Inject constructor(
    private val customerStudioMappingDao: CustomerStudioMappingDao
) {
    suspend fun save(customerStudioMapping: CustomerStudioMapping)
            = customerStudioMappingDao.insert(customerStudioMapping)

    suspend fun delete(customerStudioMapping: CustomerStudioMapping)
            = customerStudioMappingDao.delete(customerStudioMapping)

    suspend fun getAll() = customerStudioMappingDao.getAll()

    suspend fun getMappingsByCourseId(id: Int) = customerStudioMappingDao.getMappingsByStudioId(id)

    suspend fun getMappingsByCustomerId(id: Int) = customerStudioMappingDao.getMappingsByCustomerId(id)
}