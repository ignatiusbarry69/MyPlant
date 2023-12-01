package com.dicoding.myplants.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlantResponse(

	@field:SerializedName("growZoneNumber")
	val growZoneNumber: Int,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("plantId")
	val plantId: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("wateringInterval")
	val wateringInterval: Int
)