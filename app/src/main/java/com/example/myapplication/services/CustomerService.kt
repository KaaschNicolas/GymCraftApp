package com.example.myapplication.services

import com.example.myapplication.models.Customer
import javax.inject.Singleton

//Service um den aktuellen angemeldeten Customer zu erhalten
//@Singleton um als Injectable in andere Komponenten der App durch DI in jeweilige Konstruktoren übergeben zu werden
@Singleton
class CustomerService() {

    //aktuell angemeldeter Customer
    private lateinit var customer: Customer

    //gibt aktuell angemeldeter Customer zurück
    fun getCustomer(): Customer = customer

    //Setzt den aktuell angemeldeten Customer
    //Throws NullPointerException, wenn customer null ist
    fun setCustomer(customer: Customer?) {
        when (customer) {
            null -> throw NullPointerException("customer should not be empty")
            else -> {
                this.customer = customer
            }
        }
    }
}