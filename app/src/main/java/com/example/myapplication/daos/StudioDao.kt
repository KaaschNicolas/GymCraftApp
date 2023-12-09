package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.models.Studio
import kotlinx.coroutines.flow.Flow

@Dao
interface StudioDao {
    @Upsert
    suspend fun save(studio: Studio)

    @Delete
    suspend fun delete(studio: Studio)

    @Transaction
    @Query("SELECT * FROM Studio ORDER BY studioName")
    suspend fun getAll(): Flow<List<Studio>>

    @Transaction
    @Query("SELECT * FROM Studio WHERE id = :id")
    suspend fun getOneById(id: Int): Studio
}