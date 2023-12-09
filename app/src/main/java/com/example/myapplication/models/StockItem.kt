package com.example.testapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class StockItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var isUsed: Boolean = false,
    val createdDate: Date,
    val expirationDate: Date
)
