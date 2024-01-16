package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Customer
import com.example.myapplication.repositories.CustomerRepository
import com.example.myapplication.services.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Viewmodel welches die Funktionalität für MainActivity
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val customerService: CustomerService,
    private val customerRepository: CustomerRepository
): ViewModel() {

    //Überprüft, ob password und username übereinstimmen
    fun checkLogin(password: String, username: String): Boolean {
        if (password.isNullOrEmpty() || username.isNullOrEmpty()) {
            return false
        }
        val customerByUsername = customerRepository.getOneByUsername(username)
        return if (customerByUsername.password == password) {
            setUser(customerByUsername)
            true
        } else {
            false
        }
    }

    //Dummy-Aufruf an die Db, um den onCreate()-Callback auf der Gymcraftdatabase zu triggern
    fun triggerDB() {
        customerRepository.getAll()
    }

    //setzt den aktuell angemeldeten User in der App
    private fun setUser(customer: Customer) {
            customerService.setCustomer(customer)
    }
}