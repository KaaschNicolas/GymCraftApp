package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.models.Customer
import com.example.myapplication.models.CustomerAndTariff
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Upsert
    fun save(customer: Customer)

    @Query("SELECT * FROM Customer")

    fun getAll(): List<Customer>

    @Transaction
    @Query("SELECT * FROM Customer WHERE id = :id")
    fun getOneById(id: Int): Customer

    @Transaction
    @Query("SELECT * FROM Customer WHERE id = :id")
    fun getOneWithTariffById(id: Int): CustomerAndTariff
}