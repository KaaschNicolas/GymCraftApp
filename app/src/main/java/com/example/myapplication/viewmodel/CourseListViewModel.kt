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

    //Gibt alle Kurse zurück, bei denen man angemeldet ist
    private fun fillMyCourses() {
        var courseMapping = customerCourseRepository.getMappingsByCustomerId(1)
        val mappings = customerCourseRepository.getMappingsByCustomerId(customerService.getCustomer().id)
        mappings.forEach{
            myCourses.add(courseRepository.getOneById(it.courseId))
        }
    }
    //Gibt alle existierenden Kurse zurück
    private fun fillCourses() {
        courseRepository.getAll().let {
            courses = it
        }
    }
    //Ruft fillCourses() auf und gibt das Ergebnis als Liste an das Fragment zurück
     fun getCourses(): List<Course> {
        fillCourses()

        return courses
    }
    //Ruft fillMyCourses() auf und gibt das Ergebnis als Liste an das Fragment zurück
    fun getMyCourses() : List<Course> {
        myCourses = mutableListOf()
        fillMyCourses()

        return myCourses.toList()
    }
    //Gibt alle Kurse zurück, die ein gewisses Tag besitzen (Eintrag in der Brückentabelle)
    fun fillCoursesByTagId(tagId: Int){
        courseTagRepository.getMappingsByTagId(tagId).forEach{
            tagCourses.add(courseRepository.getOneById(it.courseId))
        }
    }
    //Ruft fillCoursesByTagId() auf und gibt das Ergebnis in einer Liste an das Fragment zurück
    fun getCoursesByTagId(tagId: Int): List<Course> {
        tagCourses = mutableListOf()
        fillCoursesByTagId(tagId)

        return tagCourses.toList()
    }
}