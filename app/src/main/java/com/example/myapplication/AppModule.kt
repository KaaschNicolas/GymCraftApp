package com.example.myapplication

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGymCraftDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GymCraftDatabase::class.java,
        "GymDB"
    ).build()

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