package com.example.myapplication.services

import com.example.myapplication.models.Customer
import javax.inject.Singleton

@Singleton
class CustomerService() {

    private lateinit var customer: Customer
    fun getCustomer(): Customer = customer

    fun setCustomer(customer: Customer?) {
        when (customer) {
            null -> throw NullPointerException("customer should not be empty")
            else -> {
                this.customer = customer
            }
        }
    }
}