package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.daos.CourseDao
import com.example.myapplication.daos.CustomerDao
import com.example.myapplication.models.Address
import com.example.myapplication.models.Course
import com.example.myapplication.models.Customer
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Volatile
    private var INSTANCE: GymCraftDatabase? = null

    private class DbCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    Log.i("Database Init", "OnCreate")
                    //val courses = courseDao.getAll()
                    //courses.forEach { courseDao.delete(it)}

                    val courseDao = it.getCourseDao()

                    val date = Date()

                    val course1 = Course(
                        id = 1,
                        name = "Test1",
                        description = "Description1",
                        date = date,
                        maxNumberOfEntrants = 11,
                        imageId = 1,
                    )

                    val course2 = Course(
                        id = 2,
                        name = "Test2",
                        description = "Description2",
                        date = date,
                        maxNumberOfEntrants = 12,
                        imageId = 2,
                    )

                    val course3 = Course(
                        id = 3,
                        name = "Test3",
                        description = "Description3",
                        date = date,
                        maxNumberOfEntrants =  13,
                        imageId = 3,
                    )

                    val courseList: List<Course> = arrayListOf(course1, course2, course3)
                    courseList.forEach { courseDao.save(it) }

                    val customerDao = it.getCustomerDao()

                    val customer = Customer(
                        id = 1,
                        lastName = "Kaasch",
                        firstName = "Nicolas",
                        Address(
                            id = 1,
                            city = "Karlsruhe",
                            street = "Zähringerstraße",
                            district = "Oststadt",
                            houseNumber = "5",
                            houseNumberAddition = "d",
                            postalCode = 76131,
                            mailbox = "testbox",
                        ),
                        username ="knicolas",
                        password = "Test1234",
                        email = "nicolas.kaasch@test.de",
                        birthday = date,
                        height = 1.85f,
                        weight = 80f,
                        memberSince = date,
                        memberNumber = UUID.randomUUID()
                    )

                    customerDao.save(customer)
                }
            }



        }
    }

    @Singleton
    @Provides
    fun provideGymCraftDatabase(
        @ApplicationContext app: Context,
        courseDaoProvider: Provider<CourseDao>,
    ): GymCraftDatabase {
        return INSTANCE ?: synchronized(this) {
            val scope = CoroutineScope(Dispatchers.IO)
            val instance = Room.databaseBuilder(
                app,
                GymCraftDatabase::class.java,
                "GymDB",
            )
                .allowMainThreadQueries()
                .addCallback(DbCallback(scope))
                .build()
                .also { INSTANCE = it}
            instance
        }
    }



    /* = Room.databaseBuilder(
        app,
        GymCraftDatabase::class.java,
        "GymDB"
    )
        .allowMainThreadQueries()
        .addCallback(object: RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                Log.i("Database Init", "OnCreate")
                val courseDao = courseDaoProvider.get()
                //val courses = courseDao.getAll()
                //courses.forEach { courseDao.delete(it)}

                val date = Date()

                val course1 = Course(
                    name = "Test1",
                    description = "Description1",
                    date = date,
                    maxNumberOfEntrants = 11,
                    imageId = 1,
                )

                val course2 = Course(

                    name = "Test2",
                    description = "Description2",
                    date = date,
                    maxNumberOfEntrants = 12,
                    imageId = 2,
                )

                val course3 = Course(

                    name = "Test3",
                    description = "Description3",
                    date = date,
                    maxNumberOfEntrants =  13,
                    imageId = 3,
                )

                val courseList: List<Course> = arrayListOf(course1, course2, course3)
                courseList.forEach { courseDao.save(it) }


                /*val customer = Customer(
                    id = 1,
                    lastName = "Kaasch",
                    firstName = "Nicolas",
                     Address(
                        id = 1,
                        city = "Karlsruhe",
                        street = "Zähringerstraße",
                        district = "Oststadt",
                        houseNumber = "5",
                        houseNumberAddition = "d",
                        postalCode = 76131,
                        mailbox = "testbox",
                    ),
                    username ="knicolas",
                    password = "Test1234",
                    email = "nicolas.kaasch@test.de",
                    birthday = date,
                    height = 1.85f,
                    weight = 80f,
                    memberSince = date,
                    memberNumber = UUID.randomUUID()
                ) */
            }
        })
        .build()
     */

    @Singleton
    @Provides
    fun provideCustomerDao(db: GymCraftDatabase) = db.getCustomerDao()

    @Singleton
    @Provides
    fun provideStudioDao(db: GymCraftDatabase) = db.getStudioDao()

    @Singleton
    @Provides
    fun provideTariffDao(db: GymCraftDatabase) = db.getTariffDao()

    @Singleton
    @Provides
    fun provideCourseDao(db: GymCraftDatabase) = db.getCourseDao()

    @Singleton
    @Provides
    fun provideCustomerCourseMappingDao(db: GymCraftDatabase) = db.getCustomerCourseMappingDao()

    @Singleton
    @Provides
    fun provideCustomerStudioMappingDao(db: GymCraftDatabase) = db.getCustomerStudioMappingDao()
}