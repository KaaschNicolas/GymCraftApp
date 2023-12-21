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
    fun insert(join: CustomerCourseMapping)

    @Delete
    fun delete(customerCourseMapping: CustomerCourseMapping)

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping")
    fun getAll(): CustomerCourseMapping

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping WHERE courseId = :id")
    fun getMappingsByCourseId(id: Int): CustomerCourseMapping

    @Transaction
    @Query("SELECT * FROM CustomerCourseMapping WHERE customerId = :id")
    suspend fun getMappingsByCustomerId(id: Int): CustomerCourseMapping

}