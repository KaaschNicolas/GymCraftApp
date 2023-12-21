package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Tariff::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var lastName: String,
    var firstName: String,
    @Embedded("address_")
    var address: Address,
    var username: String,
    var password: String,
    var email: String,
    var birthday: Date,
    var height: Float,
    var weight: Float,
    var memberSince: Date,
    var memberNumber: UUID,
    @Embedded(prefix = "studio_")
    var studio: Studio,
) {



}