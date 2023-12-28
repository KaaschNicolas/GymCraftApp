package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.repositories.CustomerCourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val customerCourseRepository: CustomerCourseRepository,
) : ViewModel() {

    private lateinit var myCourses: MutableList<Course>
    private lateinit var courses: List<Course>

    private fun fillMyCourses() {
        var courseMapping = customerCourseRepository.getMappingsByCustomerId(1)
        courseMapping.forEach {
            myCourses.add(courseRepository.getOneById(it.courseId))
        }
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
}