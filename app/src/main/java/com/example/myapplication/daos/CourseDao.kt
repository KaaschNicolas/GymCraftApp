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
    fun save(course: Course)

    @Delete
    fun delete(course: Course)

    @Query("SELECT * FROM Course ORDER BY name")
    fun getAll(): Course

    @Transaction
    @Query("SELECT * FROM Course WHERE id = :id")
    fun getOneById(id: Int): Course
}