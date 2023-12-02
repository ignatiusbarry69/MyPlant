package com.dicoding.myplants.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.myplants.core.domain.usecase.PlantUseCase

class FavoriteViewModel(plantUseCase: PlantUseCase): ViewModel() {
    val favoritePlant = plantUseCase.getFavoritePlant().asLiveData()
}