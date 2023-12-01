package com.dicoding.myplants.core.utils

import com.dicoding.myplants.core.data.source.local.entity.PlantEntity
import com.dicoding.myplants.core.data.source.remote.response.PlantResponse
import com.dicoding.myplants.core.domain.model.Plant


object DataMapper {
    fun mapResponsesToEntities(input: List<PlantResponse>): List<PlantEntity> {
        val plantList = ArrayList<PlantEntity>()
        input.map {
            val plant = PlantEntity(
                plantId = it.plantId,
                description = it.description,
                name = it.name,
                growZoneNumber = it.growZoneNumber,
                wateringInterval = it.wateringInterval,
                imageUrl = it.imageUrl,
                isFavorite = false
            )
            plantList.add(plant)
        }
        return plantList
    }

    fun mapEntitiesToDomain(input: List<PlantEntity>): List<Plant> =
        input.map {
            Plant(
                plantId = it.plantId,
                description = it.description,
                name = it.name,
                growZoneNumber = it.growZoneNumber,
                wateringInterval = it.wateringInterval,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Plant) = PlantEntity(
        plantId = input.plantId,
        description = input.description,
        name = input.name,
        growZoneNumber = input.growZoneNumber,
        wateringInterval = input.wateringInterval,
        imageUrl = input.imageUrl,
        isFavorite = input.isFavorite
    )
}