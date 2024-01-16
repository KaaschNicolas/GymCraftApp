package com.example.myapplication.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.models.Course
import com.example.myapplication.models.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Upsert
    fun save(tag: Tag)

    @Delete
    fun delete(tag: Tag)

    @Transaction
    @Query("SELECT * FROM Tag ORDER BY name")
    fun getAll(): List<Tag>

    @Transaction
    @Query("SELECT * FROM Course WHERE id = :id")
    fun getOneById(id: Int): Tag

    @Transaction
    @Query("SELECT * FROM Course WHERE name = :name")
    fun getOneByName(name: String): Tag
}