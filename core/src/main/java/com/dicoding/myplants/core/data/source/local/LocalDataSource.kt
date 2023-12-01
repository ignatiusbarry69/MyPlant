package com.dicoding.myplants.core.data.source.local

import com.dicoding.myplants.core.data.source.local.entity.PlantEntity
import com.dicoding.myplants.core.data.source.local.room.PlantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val plantDao: PlantDao) {

    fun getAllPlant(): Flow<List<PlantEntity>> = plantDao.getAllPlant()

    fun getFavoritePlant(): Flow<List<PlantEntity>> = plantDao.getFavoritePlant()

    suspend fun insertPlant(plantList: List<PlantEntity>) = plantDao.insertPlant(plantList)

    fun setFavoritePlant(plant: PlantEntity, newState: Boolean) {
        plant.isFavorite = newState
        plantDao.updateFavoritePlant(plant)
    }
}