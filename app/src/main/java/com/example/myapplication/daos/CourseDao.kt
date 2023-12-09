package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.models.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Upsert
    suspend fun save(course: Course)

    @Delete
    suspend fun delete(course: Course)

    @Query("SELECT * FROM Course ORDER BY name")
    suspend fun getAll(): Flow<List<Course>>

    @Transaction
    @Query("SELECT * FROM Course WHERE id = :id")
    suspend fun getOneById(id: Int): Course
}