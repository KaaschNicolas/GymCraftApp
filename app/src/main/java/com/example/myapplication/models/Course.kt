package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var name: String,
    var description: String,
    var date: Date,
    var maxNumberOfEntrants: Int,
    var imageId: Int
)