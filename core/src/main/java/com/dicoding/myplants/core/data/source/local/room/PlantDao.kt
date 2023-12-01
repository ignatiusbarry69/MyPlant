package com.dicoding.myplants.core.data.source.local.room

import androidx.room.*
import com.dicoding.myplants.core.data.source.local.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun getAllPlant(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants where isFavorite = 1")
    fun getFavoritePlant(): Flow<List<PlantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plants: List<PlantEntity>)

    @Update
    fun updateFavoritePlant(plants: PlantEntity)
}
