package com.dicoding.myplants.di

import com.dicoding.myplants.core.domain.usecase.PlantUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun plantUseCase(): PlantUseCase
}