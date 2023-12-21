package com.example.myapplication.repositories

import com.example.myapplication.daos.CourseDao
import com.example.myapplication.models.Course
import javax.inject.Inject

class CourseRepository
@Inject constructor(
        private val courseDao: CourseDao
    ) {
    suspend fun save(course: Course) = courseDao.save(course)

    suspend fun delete(course: Course) = courseDao.delete(course)

    suspend fun getAll() = courseDao.getAll()

    suspend fun getOneById(id: Int) = courseDao.getOneById(id)
}