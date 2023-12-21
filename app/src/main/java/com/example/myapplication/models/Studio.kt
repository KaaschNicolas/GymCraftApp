package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Studio(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var studioName: String,
    var postalCode: String,
    @Embedded(prefix = "address_")
    var address: Address,
    var foundingDate: Date,
    var owner: String,
)