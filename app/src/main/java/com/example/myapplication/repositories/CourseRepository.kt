package com.example.testapp.repositories

import com.example.testapp.daos.CourseDao
import com.example.testapp.models.Course
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