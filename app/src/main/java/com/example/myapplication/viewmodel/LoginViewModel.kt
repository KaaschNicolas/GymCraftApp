package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Customer
import com.example.myapplication.repositories.CustomerRepository
import com.example.myapplication.services.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val customerService: CustomerService,
    private val customerRepository: CustomerRepository
): ViewModel() {
        fun checkLogin(password: String, username: String): Boolean {
        val customerByUsername = customerRepository.getOneByUsername(username)
        return if (customerByUsername.password == password) {
            setUser(customerByUsername)
            true
        } else {
            false
        }
    }

    private fun setUser(customer: Customer) {
            customerService.setCustomer(customer)
            /*val date = Date()
            customerService.setCustomer(Customer(
                id = 1,
                lastName = "Kaasch",
                firstName = "Nicolas",
                username ="knicolas",
                password = "Test1234",
                email = "nicolas.kaasch@test.de",
                birthday = date,
                height = 1.85f,
                weight = 80f,
                memberSince = date,
                memberNumber = UUID.randomUUID()
            )) */
    }
}