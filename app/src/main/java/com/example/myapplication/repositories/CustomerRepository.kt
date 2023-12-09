package com.example.myapplication.repositories

import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.daos.CustomerDao
import com.example.myapplication.models.Customer
import javax.inject.Inject

class CustomerRepository
@Inject constructor(
    private val customerDao: CustomerDao
) {
    @Upsert
    suspend fun saveCustomer(customer: Customer) = customerDao.save(customer)

    suspend fun getAll() = customerDao.getAll()

    suspend fun getOneById(id: Int) = customerDao.getOneById(id)
}