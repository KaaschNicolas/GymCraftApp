package com.example.myapplication.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.models.CustomerStudioMapping

interface CustomerStudioMappingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: CustomerStudioMapping)

    @Delete
    fun delete(customerStudioMapping: CustomerStudioMapping)

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping")
    fun getAll(): CustomerStudioMapping

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping WHERE studioId = :id")
    fun getMappingsByStudioId(id: Int): CustomerStudioMapping

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping WHERE customerId = :id")
    fun getMappingsByCustomerId(id: Int): CustomerStudioMapping
}