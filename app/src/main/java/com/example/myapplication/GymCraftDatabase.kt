package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.converters.Converters
import com.example.myapplication.daos.CourseDao
import com.example.myapplication.daos.CustomerCourseMappingDao
import com.example.myapplication.daos.CustomerDao
import com.example.myapplication.daos.StudioDao
import com.example.myapplication.daos.TariffDao
import com.example.myapplication.models.Address
import com.example.myapplication.models.Customer
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.models.Tariff
import com.example.myapplication.models.Course
import com.example.myapplication.models.Studio

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
@TypeConverters(Converters::class)
abstract class GymCraftDatabase : RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao

    abstract fun getCustomerCourseMappingDao(): CustomerCourseMappingDao

    abstract fun getCourseDao(): CourseDao

    abstract fun getTariffDao(): TariffDao

    abstract fun getStudioDao(): StudioDao

}