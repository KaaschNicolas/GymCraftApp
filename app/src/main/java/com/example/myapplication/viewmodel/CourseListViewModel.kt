package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.models.CourseTagMapping
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.repositories.CourseTagRepository
import com.example.myapplication.repositories.CustomerCourseRepository
import com.example.myapplication.services.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val customerCourseRepository: CustomerCourseRepository,
    private val tagRepository: CourseTagRepository,
    private val courseTagRepository: CourseTagRepository,
    private val customerService: CustomerService,
) : ViewModel() {

    private lateinit var myCourses: MutableList<Course>
    private lateinit var courses: List<Course>
    private lateinit var tagCourses: MutableList<Course>

    private fun fillMyCourses() {
        var courseMapping = customerCourseRepository.getMappingsByCustomerId(customerService.getCustomer().id)
    }

    private fun fillCourses() {
        courseRepository.getAll().let {
            courses = it
        }
    }

     fun getCourses(): List<Course> {
        fillCourses()

        return courses
    }

    fun getMyCourses() : List<Course> {
        fillMyCourses()

        return myCourses.toList()
    }

    fun getCoursesByTagId(tagId: Int): List<Course> {
        courseTagRepository.getMappingsByTagId(tagId).forEach{
            tagCourses.clear()
            tagCourses.add(courseRepository.getOneById(it.courseId))
        }
        return tagCourses
    }
}