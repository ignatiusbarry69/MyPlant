package com.dicoding.myplants.di

import com.dicoding.myplants.core.domain.usecase.PlantInteractor
import com.dicoding.myplants.core.domain.usecase.PlantUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideTourismUseCase(tourismInteractor: PlantInteractor): PlantUseCase

}
