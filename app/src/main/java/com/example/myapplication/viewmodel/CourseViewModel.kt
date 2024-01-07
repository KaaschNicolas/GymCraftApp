package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.repositories.CustomerCourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val customerCourseRepository: CustomerCourseRepository,
) : ViewModel() {

    fun getCourse(id: Int): Course {
        return courseRepository.getOneById(id)
    }

    fun checkMappingExists(customerId: Int, courseId: Int?): CustomerCourseMapping? {
        val course = courseId
        val res = customerCourseRepository.checkMappingExists(customerId, courseId)
        return res
    }

    fun subscribeunsubscribe(customerId: Int, courseId: Int?): Boolean {
        val res = checkMappingExists(customerId, courseId)
        if (res !== null) {
            customerCourseRepository.delete(res)
            return true
        } else {
            courseId?.let()
            {
                customerCourseRepository.save(CustomerCourseMapping(
                    courseId = it,
                    customerId = customerId,
                ))
            }
            return false
        }
    }
    fun countParticipants(courseId: Int = 1): Int {
        return customerCourseRepository.getMappingsByCourseId(courseId).size
    }
}