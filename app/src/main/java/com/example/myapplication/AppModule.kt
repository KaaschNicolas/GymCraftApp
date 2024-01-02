package com.example.myapplication

import android.content.Context
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
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGymCraftDatabase(
        @ApplicationContext app: Context,
        courseDaoProvider: Provider<CourseDao>,
    ) = Room.databaseBuilder(
        app,
        GymCraftDatabase::class.java,
        "GymDB"
    )
        .allowMainThreadQueries()
        .addCallback(object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val courseDao = courseDaoProvider.get()
                val courses = courseDao.getAll()
                courses.forEach { courseDao.delete(it)}

                val date = Date()

                val course1 = Course(
                    id = 1,
                    name = "Spinning",
                    description = "Join our fun spinning classes at the gym! Pedal to the beat, burn calories, and boost your fitness in a lively group atmosphere.",
                    date,
                    maxNumberOfEntrants = 20,
                    imageId = 1,
                )

                val course2 = Course(
                    id = 2,
                    name = "Yoga",
                    description = "Unwind and rejuvenate in our Relax & Stretch Yoga class. Perfect for beginners and seasoned yogis alike, this session focuses on gentle stretches, deep breathing, and fostering a sense of calm for a balanced mind and body.",
                    date,
                    maxNumberOfEntrants = 12,
                    imageId = 2,
                )

                val course3 = Course(
                    id = 3,
                    name = "Zumba",
                    description = "\"Dance away the calories with our Zumba Fiesta! Join the party, move to infectious beats, and sweat your way to fitness – because workouts are better when they feel like a celebration!\"",
                    date,
                    maxNumberOfEntrants =  13,
                    imageId = 3,
                )

                val course4 = Course(
                    id = 4,
                    name = "Cardio Kickboxing",
                    description = "Unleash your inner warrior in Cardio Kickboxing! Channel your stress into powerful punches and kicks while getting an intense cardio workout. Don't be surprised if you leave feeling like a superhero.",
                    date,
                    maxNumberOfEntrants =  15,
                    imageId = 3,
                )

                val course5 = Course(
                    id = 5,
                    name = "Zumba",
                    description = "\"Dance away the calories with our Zumba Fiesta! Join the party, move to infectious beats, and sweat your way to fitness – because workouts are better when they feel like a celebration!\"",
                    date,
                    maxNumberOfEntrants =  13,
                    imageId = 3,
                )

                val course6 = Course(
                    id = 6,
                    name = "Stretch & Relax",
                    description = "Ease tension and enhance flexibility in Stretch & Relax. This class is all about gentle stretches and relaxation techniques to help you unwind and leave feeling refreshed.",
                    date,
                    maxNumberOfEntrants =  10,
                    imageId = 3,
                )

                val course7 = Course(
                    id = 3,
                    name = "Bodyweight",
                    description = "No fancy equipment needed! Join Bodyweight Basics to learn effective exercises using just your own body weight. It's a simple yet powerful way to build strength and endurance.",
                    date,
                    maxNumberOfEntrants =  13,
                    imageId = 3,
                )

                val courseList: List<Course> = arrayListOf(course1, course2, course3, course4, course5, course6, course7)
                courseList.forEach { courseDao.save(it) }

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
            }
        })
        .build()

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