package com.example.myapplication.models

import androidx.room.Entity

@Entity(primaryKeys = ["courseId", "tagId"])
data class CourseTagMapping(
    val courseId: Int,
    val tagId: Int
)