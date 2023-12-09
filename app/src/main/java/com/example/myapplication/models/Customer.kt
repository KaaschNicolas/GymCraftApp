package com.example.testapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var lastName: String,
    var firstName: String,
    @Embedded
    var address: Address,
    var username: String,
    var password: String,
    var email: String,
    var birthday: Date,
    var height: Float,
    var weight: Float,
    var memberSince: Date,
    var memberNumber: UUID,
    @Embedded
    var studio: Studio,
    @Embedded
    var tariff: Tariff
) {



}