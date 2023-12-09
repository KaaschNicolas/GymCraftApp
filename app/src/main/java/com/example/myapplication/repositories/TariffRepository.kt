package com.example.myapplication.repositories

import com.example.myapplication.daos.TariffDao
import com.example.myapplication.models.Tariff
import javax.inject.Inject

class TariffRepository
@Inject constructor(
    private val tariffDao: TariffDao
) {
    suspend fun save(tariff: Tariff) = tariffDao.saveTariff(tariff)

    suspend fun delete(tariff: Tariff) = tariffDao.deleteTariff(tariff)

    suspend fun getAll() = tariffDao.getAll()

    suspend fun getOneById(id: Int) = tariffDao.getOneById(id)
}