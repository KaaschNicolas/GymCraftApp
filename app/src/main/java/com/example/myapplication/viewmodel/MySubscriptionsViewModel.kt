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

    //hole alle Tarife und gebe sie aus einer Liste aus
    private fun fillTariffs(): List<Tariff> {
        tariffRepository.getAll().let {
            return it
        }
    }

    //gebe die tarife für das Programm zurück
    fun getTariffs(): List<Tariff> {
        return fillTariffs()
    }

    fun getMyTariffs() : List<Tariff> {
        fillTariffs()

        return myTariff.toList()
    }
}