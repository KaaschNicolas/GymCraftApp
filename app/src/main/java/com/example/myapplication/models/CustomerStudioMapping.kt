package com.example.myapplication.models

import androidx.room.Entity

@Entity(primaryKeys = ["studioId", "customerId"])
data class CustomerStudioMapping(
    val studioId: Int,
    val customerId: Int,
)