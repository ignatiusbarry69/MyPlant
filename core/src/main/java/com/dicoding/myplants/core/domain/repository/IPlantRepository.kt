package com.dicoding.myplants.core.domain.repository

import com.dicoding.myplants.core.data.Resource
import com.dicoding.myplants.core.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface IPlantRepository {

    fun getAllPlant(): Flow<Resource<List<Plant>>>

    fun getFavoritePlant(): Flow<List<Plant>>

    fun setFavoritePlant(plant: Plant, state: Boolean)

}