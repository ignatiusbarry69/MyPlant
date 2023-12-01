package com.dicoding.myplants.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myplants.core.domain.model.Plant
import com.dicoding.myplants.core.domain.usecase.PlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlantViewModel @Inject constructor(private val plantUseCase: PlantUseCase) : ViewModel() {
    fun setFavoritePlant(plant: Plant, newStatus:Boolean) =
        viewModelScope.launch(Dispatchers.IO){
            plantUseCase.setFavoritePlant(plant, newStatus)
        }
}

