package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String
)