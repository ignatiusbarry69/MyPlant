package com.dicoding.myplants.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey @NonNull @ColumnInfo (name = "id") var plantId: String,
    var name: String,
    var description: String,
    var growZoneNumber: Int,
    var wateringInterval: Int = 7,
    var imageUrl: String = "",
    var isFavorite: Boolean = false
)