package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Course
import com.example.myapplication.models.Tariff
import com.example.myapplication.repositories.CustomerCourseRepository
import com.example.myapplication.repositories.TariffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MySubscriptionsViewModel @Inject constructor(
    private val tariffRepository: TariffRepository,
) : ViewModel() {

    private lateinit var myTariff: MutableList<Tariff>
    private lateinit var tariffs: List<Tariff>

    private fun fillTariffs() {
        tariffRepository.getAll().let {
            tariffs = it
        }
    }

    fun getTariffs(): List<Tariff> {
        fillTariffs()

        return tariffs
    }

    fun getMyTariffs() : List<Tariff> {
        fillTariffs()

        return myTariff.toList()
    }
}