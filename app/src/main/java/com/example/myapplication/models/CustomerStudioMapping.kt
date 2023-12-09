package com.example.testapp.models

import androidx.room.Entity

@Entity(primaryKeys = ["customerId", "studioId"])
data class CustomerStudioMapping(
    val customerId: Int,
    val studioId: Int,
)