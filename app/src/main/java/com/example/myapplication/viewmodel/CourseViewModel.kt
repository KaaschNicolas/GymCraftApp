package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.repositories.CustomerCourseRepository
import com.example.myapplication.services.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CourseViewModel @Inject constructor(
    private val customerService: CustomerService,
    private val courseRepository: CourseRepository,
    private val customerCourseRepository: CustomerCourseRepository,
) : ViewModel() {

    fun getCourse(id: Int): Course {
        return courseRepository.getOneById(id)
    }

    fun checkMappingExists(courseId: Int?): CustomerCourseMapping? {
        val customer = customerService.getCustomer()
        return customerCourseRepository.checkMappingExists(customer.id, courseId)
    }

    fun subscribeunsubscribe( courseId: Int?): Boolean {
        val customer = customerService.getCustomer()
        val res = checkMappingExists(courseId)
        if (res !== null) {
            customerCourseRepository.delete(res)
            return true
        } else {
            courseId?.let()
            {
                customerCourseRepository.save(CustomerCourseMapping(
                    courseId = it,
                    customerId = customer.id,
                ))
            }
            return false
        }
    }
    fun countParticipants(courseId: Int = 1): Int {
        return customerCourseRepository.getMappingsByCourseId(courseId).size
    }
}