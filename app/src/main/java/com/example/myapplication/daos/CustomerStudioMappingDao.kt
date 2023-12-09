package com.example.myapplication.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.models.CustomerStudioMapping
import kotlinx.coroutines.flow.Flow

interface CustomerStudioMappingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(join: CustomerStudioMapping)

    @Delete
    suspend fun delete(customerStudioMapping: CustomerStudioMapping)

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping")
    suspend fun getAll(): Flow<List<CustomerStudioMapping>>

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping WHERE studioId = :id")
    suspend fun getMappingsByStudioId(id: Int): Flow<List<CustomerStudioMapping>>

    @Transaction
    @Query("SELECT * FROM CustomerStudioMapping WHERE customerId = :id")
    suspend fun getMappingsByCustomerId(id: Int): Flow<List<CustomerStudioMapping>>
}