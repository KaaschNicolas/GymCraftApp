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
    fun saveCustomer(customer: Customer) = customerDao.save(customer)

    fun getAll() = customerDao.getAll()

    fun getOneById(id: Int) = customerDao.getOneById(id)

    //suspend fun getOneWithTariffById(id: Int) = customerDao.getOneWithTariffById(id)
}