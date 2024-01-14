package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.models.CourseTagMapping
import com.example.myapplication.models.CustomerStudioMapping

@Dao
interface CourseTagMappingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: CourseTagMapping)

    @Delete
    fun delete(courseTagMapping: CourseTagMapping)

    @Transaction
    @Query("SELECT * FROM CourseTagMapping")
    fun getAll(): CourseTagMapping

    @Transaction
    @Query("SELECT * FROM CourseTagMapping WHERE courseId = :id")
    fun getMappingsByCourseId(id: Int): CourseTagMapping

    @Transaction
    @Query("SELECT * FROM CourseTagMapping WHERE tagId = :id")
    fun getMappingsByTagId(id: Int): List<CourseTagMapping>
}