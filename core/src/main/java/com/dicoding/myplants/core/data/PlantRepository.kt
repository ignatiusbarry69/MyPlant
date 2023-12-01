package com.dicoding.myplants.core.data

import com.dicoding.myplants.core.data.source.local.LocalDataSource
import com.dicoding.myplants.core.data.source.remote.RemoteDataSource
import com.dicoding.myplants.core.data.source.remote.network.ApiResponse
import com.dicoding.myplants.core.data.source.remote.response.PlantResponse
import com.dicoding.myplants.core.domain.model.Plant
import com.dicoding.myplants.core.domain.repository.IPlantRepository
import com.dicoding.myplants.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPlantRepository {

    override fun getAllPlant(): Flow<Resource<List<Plant>>> =
        object : NetworkBoundResource<List<Plant>, List<PlantResponse>>() {
            override fun loadFromDB(): Flow<List<Plant>> {
                return localDataSource.getAllPlant().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Plant>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<PlantResponse>>> =
                remoteDataSource.getAllPlant()

            override suspend fun saveCallResult(data: List<PlantResponse>) {
                val plantList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPlant(plantList)
            }
        }.asFlow()

    override fun getFavoritePlant(): Flow<List<Plant>> {
        return localDataSource.getFavoritePlant().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoritePlant(plant: Plant, state: Boolean) {
        val plantEntity = DataMapper.mapDomainToEntity(plant)
        localDataSource.setFavoritePlant(plantEntity, state)
    }
}
