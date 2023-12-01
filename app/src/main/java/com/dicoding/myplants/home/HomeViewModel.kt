package com.dicoding.myplants.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.myplants.core.domain.usecase.PlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(tourismUseCase: PlantUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllPlant().asLiveData()
}

