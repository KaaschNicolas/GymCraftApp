package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.daos.CourseDao
import com.example.myapplication.daos.StudioDao
import com.example.myapplication.models.Address
import com.example.myapplication.models.Course
import com.example.myapplication.models.CourseTagMapping
import com.example.myapplication.models.Studio
import com.example.myapplication.models.Customer
import com.example.myapplication.models.Tag
import com.example.myapplication.services.CustomerService
import com.example.myapplication.models.Tariff
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        private val scope: CoroutineScope,
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

                    val date1 = "18.01.2023 18:00"
                    val date2 = "19.01.2023 19:00"
                    val date3 = "19.01.2023 20:00"
                    val date4 = "21.01.2023 18:00"
                    val date5 = "22.01.2023 16:00"
                    val date6 = "22.01.2023 18:00"
                    val date7 = "23.01.2023 20:00"

                    val format = SimpleDateFormat("dd.MM.yyyy HH:mm")

                    val drawable1 = R.drawable.course_sample

                    val course1 = Course(
                        id = 1,
                        name = "Spinning",
                        description = "Join our fun spinning classes at the gym! Pedal to the beat, burn calories, and boost your fitness in a lively group atmosphere.",
                        date = format.parse(date1),
                        maxNumberOfEntrants = 20,
                        imageId = drawable1,
                    )

                    val drawable2 = R.drawable.yoga

                    val course2 = Course(
                        id = 2,
                        name = "Yoga",
                        description = "Unwind and rejuvenate in our Relax & Stretch Yoga class. Perfect for beginners and seasoned yogis alike, this session focuses on gentle stretches, deep breathing, and fostering a sense of calm for a balanced mind and body.",
                        date = format.parse(date2),
                        maxNumberOfEntrants = 12,
                        imageId = drawable2,
                    )

                    val drawable3 = R.drawable.zumba

                    val course3 = Course(
                        id = 3,
                        name = "Zumba",
                        description = "Dance away the calories with our Zumba Fiesta! Join the party, move to infectious beats, and sweat your way to fitness – because workouts are better when they feel like a celebration!\"",
                        date = format.parse(date3),
                        maxNumberOfEntrants =  13,
                        imageId = drawable3,
                    )

                    val drawable4 = R.drawable.cardio_boxing

                    val course4 = Course(
                        id = 4,
                        name = "Kickboxing",
                        description = "Unleash your inner warrior in Cardio Kickboxing! Channel your stress into powerful punches and kicks while getting an intense cardio workout. Don't be surprised if you leave feeling like a superhero.",
                        date = format.parse(date4),
                        maxNumberOfEntrants =  15,
                        imageId = drawable4,
                    )



                    val course5 = Course(
                        id = 5,
                        name = "Zumba",
                        description = "Dance away the calories with our Zumba Fiesta! Join the party, move to infectious beats, and sweat your way to fitness – because workouts are better when they feel like a celebration!\"",
                        date= format.parse(date5),
                        maxNumberOfEntrants =  13,
                        imageId = drawable3,
                    )

                    val drawable5 = R.drawable.stretchurelax

                    val course6 = Course(
                        id = 6,
                        name = "Stretching",
                        description = "Ease tension and enhance flexibility in Stretch & Relax. This class is all about gentle stretches and relaxation techniques to help you unwind and leave feeling refreshed.",
                        date=format.parse(date6),
                        maxNumberOfEntrants =  10,
                        imageId = drawable5,
                    )

                    val drawable6 = R.drawable.bodyweight

                    val course7 = Course(
                        id = 7,
                        name = "Bodyweight",
                        description = "No fancy equipment needed! Join Bodyweight Basics to learn effective exercises using just your own body weight. It's a simple yet powerful way to build strength and endurance.",
                        date = format.parse(date7),
                        maxNumberOfEntrants =  13,
                        imageId = drawable6,
                    )

                    val courseList: List<Course> = arrayListOf(course1, course2, course3, course4, course5, course6, course7)
                    courseList.forEach { courseDao.save(it) }

                    val customerDao = it.getCustomerDao()

                    val customer1 = Customer(
                        id = 1,
                        lastName = "Kaasch",
                        firstName = "Nicolas",
                        username ="knicolas",
                        password = "Test1234",
                        email = "nicolas.kaasch@test.de",
                        birthday = date,
                        height = 1.85f,
                        weight = 80f,
                        memberSince = date,
                        memberNumber = UUID.randomUUID()
                    )

                    val customer2 = Customer(
                        id = 2,
                        lastName = "Muskelmann",
                        firstName = "Moritz",
                        username ="moritzkhn",
                        password = "Abc1234",
                        email = "moritzkhn@test.de",
                        birthday = date,
                        height = 1.80f,
                        weight = 78f,
                        memberSince = date,
                        memberNumber = UUID.randomUUID()
                    )
                    val customerList: List<Customer> = arrayListOf(customer1, customer2)
                    customerList.forEach { customerDao.save(it) }

                    val studioDao = it.getStudioDao()
                    val studio1 = Studio(
                        id = 1,
                        studioName = "PumpGym",
                        postalCode = "76133",
                        Address(
                            id = 4,
                            city = "Karlsruhe",
                            street = "Kaiserstraße",
                            district = "Innenstadt",
                            houseNumber = "5",
                            houseNumberAddition = "d",
                            postalCode = 76133,
                            mailbox = "testbox",
                        ),
                        foundingDate =  Date(),
                        owner = "Markus Rühl",
                        openingHours = "Mo-Fr 10-21 Uhr Sa-So 9-22 Uhr",
                        description = "Kommt heute zu uns und trainiert für eure Gains! :)"
                    )
                    studioDao.save(studio1)


                    val tariff1 = Tariff(
                        id = 1,
                        price = 29.99f,
                        duration = date,
                        description = "Basistariff",
                        customerId = 1,
                    )
                    val tariff2 = Tariff(
                        id = 2,
                        price = 31.98f,
                        duration = date,
                        description = "Ernährungsberatung",
                        customerId = 2,
                    )
                    val tariff3 = Tariff(
                        id = 3,
                        price = 33.78f,
                        duration = date,
                        description = "BasisTariff+",
                        customerId = 3,
                    )
                    val tariff4 = Tariff(
                        id = 4,
                        price = 36.98f,
                        duration = date,
                        description = "Tariff+ Massage",
                        customerId = 4,
                    )
                    val tariff5 = Tariff(
                        id = 5,
                        price = 100.99f,
                        duration = date,
                        description = "Permium Tariff",
                        customerId = 5,
                    )
                    val tariff6 = Tariff(
                        id = 6,
                        price = 46.98f,
                        duration = date,
                        description = "Tariff+ Personal",
                        customerId = 6,
                    )
                    val tariff7 = Tariff(
                        id = 7,
                        price = 33.99f,
                        duration = date,
                        description = "Tariff Sauna",
                        customerId = 7,
                    )
                    val tariffDao = it.getTariffDao()
                    val tariffList: List<Tariff> = arrayListOf(tariff1, tariff2, tariff3, tariff4, tariff5, tariff6, tariff7)
                    tariffList.forEach { tariffDao.saveTariff(it) }
                    val tagDao = it.getTagDao()
                    val strengthTrainingTag = Tag(
                        id = 1,
                        name = "StrengthTraining"
                    )
                    val cardioTag = Tag(
                        id = 2,
                        name = "Cardio"
                    )
                    val stretchingTag = Tag(
                        id = 3,
                        name = "Stretching"
                    )
                    val tagList = arrayListOf<Tag>(strengthTrainingTag, cardioTag, stretchingTag)
                    tagList.forEach{ tagDao.save(it)}

                    val courseTagMappingDao = it.getCourseTagMappingDao()
                    val mappings = arrayListOf<CourseTagMapping>(
                        CourseTagMapping(1,1),
                        CourseTagMapping(1,2),
                        CourseTagMapping(2, 1),
                        CourseTagMapping(2, 3),
                        CourseTagMapping(3, 2),
                        CourseTagMapping(4,2),
                        CourseTagMapping(5,2),
                        CourseTagMapping(6,3),
                        CourseTagMapping(7,1),
                        CourseTagMapping(7,2)

                    )
                    mappings.forEach { courseTagMappingDao.save(it) }
                }
            }



        }
    }

    @Singleton
    @Provides
    fun provideGymCraftDatabase(
        @ApplicationContext app: Context,
        courseDaoProvider: Provider<CourseDao>,
        studioDaoProvider: Provider<StudioDao>,
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

    @Singleton
    @Provides
    fun provideCustomerSerivce(): CustomerService = CustomerService()

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

    @Singleton
    @Provides
    fun provideTagDao(db: GymCraftDatabase) = db.getTagDao()

    @Singleton
    @Provides
    fun provideCourseTagDao(db: GymCraftDatabase) = db.getCourseTagMappingDao()
}