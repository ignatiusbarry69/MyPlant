package com.dicoding.myplants.core.data.source.remote

import android.util.Log
import com.dicoding.myplants.core.data.source.remote.network.ApiResponse
import com.dicoding.myplants.core.data.source.remote.network.ApiService
import com.dicoding.myplants.core.data.source.remote.response.PlantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPlant(): Flow<ApiResponse<List<PlantResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

