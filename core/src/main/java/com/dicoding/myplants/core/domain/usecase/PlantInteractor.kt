package com.dicoding.myplants.core.domain.usecase

import com.dicoding.myplants.core.domain.model.Plant
import com.dicoding.myplants.core.domain.repository.IPlantRepository
import javax.inject.Inject

class PlantInteractor @Inject constructor(private val plantRepository: IPlantRepository): PlantUseCase {

    override fun getAllPlant() = plantRepository.getAllPlant()

    override fun getFavoritePlant() = plantRepository.getFavoritePlant()

    override fun setFavoritePlant(plant: Plant, state: Boolean) = plantRepository.setFavoritePlant(plant, state)
}