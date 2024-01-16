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

    //Rückgabe eines Kurses mithilfe der ID
    fun getCourse(id: Int): Course {
        return courseRepository.getOneById(id)
    }
    //Rückgabe der Informationen zu eines Nutzers zu einem Kurs
    fun checkMappingExists(courseId: Int?): CustomerCourseMapping? {
        val customer = customerService.getCustomer()
        return customerCourseRepository.checkMappingExists(customer.id, courseId)
    }
    //Meldet den aktuellen Nutzer bei einem Kurs an oder ab. Dafür wird der Eintrag in der zugehörigen Brückentabelle entweder angelegt oder gelöscht.
    //Ein Bool-Wert wird zurückgegeben um zu wissen, welche Infomeldung gezeigt und wie der An-/Abmeldebutton angezeigt werden soll
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
    //Gibt die Anzahl der jeweiligen Teilnehmer des übergebenen Kurses zurück
    fun countParticipants(courseId: Int = 1): Int {
        return customerCourseRepository.getMappingsByCourseId(courseId).size
    }
}