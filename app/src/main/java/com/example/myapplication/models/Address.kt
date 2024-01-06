package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var city: String,
    var street: String,
    var district: String,
    var houseNumber: String,
    var houseNumberAddition: String,
    var postalCode: Int,
    var mailbox: String,
)