package com.dicoding.myplants.core.data.source.remote.network

import com.dicoding.myplants.core.data.source.remote.response.PlantResponse
import retrofit2.http.GET

interface ApiService {
    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getList(): List<PlantResponse>
}
