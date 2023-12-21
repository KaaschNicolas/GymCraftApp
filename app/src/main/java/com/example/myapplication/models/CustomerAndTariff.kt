package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerAndTariff(
    @Embedded()
    val customer: Customer,
    @Relation(
        entity = Tariff::class,
        parentColumn = "id",
        entityColumn = "customerId"
    )
    val tariff: Tariff,
)