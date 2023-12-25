package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.repositories.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseRepository: CourseRepository,

) : ViewModel() {

    private lateinit var courses: List<Course>

    private suspend fun fillCourses() {
        courseRepository.getAll().let {it ->
            courses = it
        }
    }

     fun getCourses(): List<Course> {
        runBlocking {
            fillCourses()
        }

        return courses
    }
}