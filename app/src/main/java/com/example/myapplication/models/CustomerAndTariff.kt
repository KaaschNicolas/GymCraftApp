package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerAndTariff(
    @Embedded()
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customer_Id"
    )
    val tariff: Tariff
)

