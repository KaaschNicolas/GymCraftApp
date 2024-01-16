package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.converters.Converters
import com.example.myapplication.daos.CourseDao
import com.example.myapplication.daos.CourseTagMappingDao
import com.example.myapplication.daos.CustomerCourseMappingDao
import com.example.myapplication.daos.CustomerDao
import com.example.myapplication.daos.CustomerStudioMappingDao
import com.example.myapplication.daos.StudioDao
import com.example.myapplication.daos.TagDao
import com.example.myapplication.daos.TariffDao
import com.example.myapplication.models.Address
import com.example.myapplication.models.Customer
import com.example.myapplication.models.CustomerCourseMapping
import com.example.myapplication.models.Tariff
import com.example.myapplication.models.Course
import com.example.myapplication.models.CustomerStudioMapping
import com.example.myapplication.models.Studio
import com.example.myapplication.models.Tag
import com.example.myapplication.models.CourseTagMapping

//Room-Databse Klasse, welche alle Entitäten in der Datenbank angibt und alle Daos implementiert
@Database(
    entities =
    [
        CustomerStudioMapping::class,
        CustomerCourseMapping::class,
        Tariff::class,
        Customer::class,
        Course::class,
        Studio::class,
        Address::class,
        Tag::class,
        CourseTagMapping::class,
       ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class GymCraftDatabase : RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao

    abstract fun getCustomerCourseMappingDao(): CustomerCourseMappingDao

    abstract fun getCustomerStudioMappingDao(): CustomerStudioMappingDao

    abstract fun getCourseDao(): CourseDao

    abstract fun getTariffDao(): TariffDao

    abstract fun getStudioDao(): StudioDao

    abstract fun getTagDao(): TagDao

    abstract fun getCourseTagMappingDao(): CourseTagMappingDao

}