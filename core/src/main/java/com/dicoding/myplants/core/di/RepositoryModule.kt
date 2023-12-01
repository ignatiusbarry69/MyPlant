package com.dicoding.myplants.core.di

import com.dicoding.myplants.core.data.PlantRepository
import com.dicoding.myplants.core.domain.repository.IPlantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(plantRepository: PlantRepository): IPlantRepository

}