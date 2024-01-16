package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Customer
import com.example.myapplication.models.Tariff
import com.example.myapplication.repositories.CustomerRepository
import com.example.myapplication.repositories.CustomerStudioRepository
import com.example.myapplication.repositories.TariffRepository
import com.example.myapplication.services.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel
@Inject constructor(
    private val customerRepository: CustomerRepository,
    private val customerService: CustomerService,
    private val studioRepository: CustomerStudioRepository,
    private val tariffRepository: TariffRepository,
) : ViewModel() {

    private lateinit var customer: Customer
    private lateinit var tariff: Tariff

    private suspend fun getCurrentCustomer(id: Int): Customer? {
        if (id == null) {
            return null
        }
        return customerRepository.getOneById(id)
    }

    private suspend fun getCurrentTariff(customerId: Int): Tariff? {
        if (customerId == null) {
            return null
        }
        return tariffRepository.getOneByCustomerId(customerId)
    }

    // ID aus dem Anmeldekontext abrufen
    suspend fun initCustomer(id: Int) {
        getCurrentCustomer(id).let { customer ->
            if (customer != null) {
                this.customer = customer
                getCurrentTariff(this.customer.id).let {
                    if (it != null) {
                        tariff = it
                    } else {
                        //if null do handling here
                    }
                }
            } else {
                //if null do handling here (e.g. errorMessage)
            }
        }

    }
    //Den Customer bekommen und als User weitergeben
    fun getUser(): Customer {
        customerService.getCustomer().let {
            return it
        }
    }
    //das Datum vereinfacht zurückgeben
    fun dateToReadableDate(date: Date?): String? {
        val dateFormat = SimpleDateFormat("DD-MM-YYYY")

        return date?.let { dateFormat.format(it) }
    }
}