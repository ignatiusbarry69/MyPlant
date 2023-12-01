package com.dicoding.myplants.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.myplants.core.data.source.local.room.PlantDao
import com.dicoding.myplants.core.data.source.local.room.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PlantDatabase = Room.databaseBuilder(
        context,
        PlantDatabase::class.java, "Plant.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePlantDao(database: PlantDatabase): PlantDao = database.plantDao()
}