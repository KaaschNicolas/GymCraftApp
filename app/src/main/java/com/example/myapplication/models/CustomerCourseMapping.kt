package com.example.testapp.models

import androidx.room.Entity

@Entity(primaryKeys = ["courseId", "customerId"])
data class CustomerCourseMapping(
    val courseId: Int,
    val customerId: Int,
)