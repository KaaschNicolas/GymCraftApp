package com.example.myapplication.repositories

import com.example.myapplication.daos.CustomerStudioMappingDao
import com.example.myapplication.models.CustomerStudioMapping
import javax.inject.Inject

class CustomerStudioRepository
@Inject constructor(
    private val customerStudioMappingDao: CustomerStudioMappingDao
) {
    fun save(customerStudioMapping: CustomerStudioMapping)
            = customerStudioMappingDao.insert(customerStudioMapping)

    fun delete(customerStudioMapping: CustomerStudioMapping)
            = customerStudioMappingDao.delete(customerStudioMapping)

    fun getAll() = customerStudioMappingDao.getAll()

    fun getMappingsByCourseId(id: Int) = customerStudioMappingDao.getMappingsByStudioId(id)

    fun getMappingsByCustomerId(id: Int) = customerStudioMappingDao.getMappingsByCustomerId(id)
}