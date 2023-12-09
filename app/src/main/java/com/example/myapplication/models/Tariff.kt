package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Tariff(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var price: Float,
    var duration: Date,
    var description: String,
)