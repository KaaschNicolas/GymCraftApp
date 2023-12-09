package com.example.testapp.repositories

import com.example.testapp.daos.TariffDao
import com.example.testapp.models.Tariff
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