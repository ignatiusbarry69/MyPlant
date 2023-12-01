package com.dicoding.myplants.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.myplants.core.data.source.local.entity.PlantEntity

@Database(entities = [PlantEntity::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

}