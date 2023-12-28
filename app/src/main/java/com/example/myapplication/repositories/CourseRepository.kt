package com.example.myapplication.repositories

import com.example.myapplication.daos.CourseDao
import com.example.myapplication.models.Course
import javax.inject.Inject

class CourseRepository
@Inject constructor(
        private val courseDao: CourseDao
    ) {
    fun save(course: Course) = courseDao.save(course)

    fun delete(course: Course) = courseDao.delete(course)

    fun getAll() = courseDao.getAll()

    fun getOneById(id: Int) = courseDao.getOneById(id)

}