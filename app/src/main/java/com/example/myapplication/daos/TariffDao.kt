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
    fun saveTariff(tariff: Tariff)

    @Delete
    fun deleteTariff(tariff: Tariff)

    @Query("SELECT * FROM Tariff ORDER BY price")
    fun getAll(): Tariff

    @Transaction
    @Query("SELECT * FROM Tariff WHERE id = :id")
    fun getOneById(id: Int): Tariff

    @Transaction
    @Query("SELECT * FROM Tariff WHERE customerId = :id")
    fun getOneByCustomerId(id: Int): Tariff
}