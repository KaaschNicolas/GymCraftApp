package com.example.myapplication.repositories

import com.example.myapplication.daos.TariffDao
import com.example.myapplication.models.Tariff
import javax.inject.Inject

class TariffRepository
@Inject constructor(
    private val tariffDao: TariffDao
) {
    fun save(tariff: Tariff) = tariffDao.saveTariff(tariff)

    fun delete(tariff: Tariff) = tariffDao.deleteTariff(tariff)

    fun getAll(): List<Tariff> = tariffDao.getAll()

    fun getOneById(id: Int) = tariffDao.getOneById(id)

    fun getOneByCustomerId(id: Int) = tariffDao.getOneByCustomerId(id)

}