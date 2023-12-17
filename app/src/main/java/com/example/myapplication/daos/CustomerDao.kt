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
    suspend fun save(customer: Customer)

    @Query("SELECT * FROM Customer ORDER BY firstName")
    suspend fun getAll(): Flow<List<Customer>>

    @Transaction
    @Query("SELECT * FROM Customer WHERE id = :id")
    suspend fun getOneById(id: Int): Customer

    @Transaction
    @Query("SELECT * FROM Customer WHERE id = :id")
    suspend fun getOneWithTariffById(id: Int): CustomerAndTariff
}