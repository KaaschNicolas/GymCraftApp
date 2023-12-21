package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.models.Tariff
import kotlinx.coroutines.flow.Flow

@Dao
interface TariffDao {
    @Upsert
    suspend fun saveTariff(tariff: Tariff)

    @Delete
    suspend fun deleteTariff(tariff: Tariff)

    @Query("SELECT * FROM Tariff ORDER BY price")
    suspend fun getAll(): Flow<Tariff>

    @Transaction
    @Query("SELECT * FROM Tariff WHERE id = :id")
    suspend fun getOneById(id: Int): Flow<Tariff>

    @Transaction
    @Query("SELECT * FROM Tariff WHERE customerId = :id")
    suspend fun getOneByCustomerId(id: Int): Flow<Tariff>
}