package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.models.CustomerCourseMapping
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerCourseMappingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(join: CustomerCourseMapping)

    @Delete
    suspend fun delete(customerCourseMapping: CustomerCourseMapping)

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping")
    suspend fun getAll(): Flow<List<CustomerCourseMapping>>

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping WHERE courseId = :id")
    suspend fun getMappingsByCourseId(id: Int): Flow<List<CustomerCourseMapping>>

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping WHERE customerId = :id")
    suspend fun getMappingsByCustomerId(id: Int): Flow<List<CustomerCourseMapping>>

}