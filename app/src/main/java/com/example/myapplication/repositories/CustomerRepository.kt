package com.example.myapplication.repositories

import androidx.room.Upsert
import com.example.myapplication.daos.CustomerDao
import com.example.myapplication.models.Customer
import javax.inject.Inject

//Repository zum gekapselten Zugriff auf CustomerRepository
class CustomerRepository
@Inject constructor(
    private val customerDao: CustomerDao
) {
    @Upsert
    fun saveCustomer(customer: Customer) = customerDao.save(customer)

    fun getAll() = customerDao.getAll()

    fun getOneById(id: Int) = customerDao.getOneById(id)

    fun getOneByUsername(username: String) = customerDao.getOneByUsername(username)

    //suspend fun getOneWithTariffById(id: Int) = customerDao.getOneWithTariffById(id)
}