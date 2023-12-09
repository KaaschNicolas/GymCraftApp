package com.example.testapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.daos.CourseDao
import com.example.testapp.daos.CustomerCourseMappingDao
import com.example.testapp.daos.CustomerDao
import com.example.testapp.daos.StudioDao
import com.example.testapp.daos.TariffDao
import com.example.testapp.models.Address
import com.example.testapp.models.Customer
import com.example.testapp.models.CustomerCourseMapping
import com.example.testapp.models.Tariff
import com.example.testapp.models.Course
import com.example.testapp.models.Studio

@Database(
    entities = [Customer::class,
        CustomerCourseMapping::class,
        Tariff::class,
        Customer::class,
        Course::class,
        Studio::class,
        Address::class,
       ],
    version = 1
)
abstract class GymCraftDatabase : RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao

    abstract fun getCustomerCourseMappingDao(): CustomerCourseMappingDao

    abstract fun getCourseDao(): CourseDao

    abstract fun getTariffDao(): TariffDao

    abstract fun getStudioDao(): StudioDao

}