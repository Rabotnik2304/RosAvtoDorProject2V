package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.MyPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MapRemoteDataSource {

    private val BASE_URL = "http://example.com/api/Mobile/"
    private val mapPointsApi: MapPointsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MapPointsApi::class.java)
    }

    private val points: MutableList<MyPoint> = mutableListOf()

    fun loadPoints() = points
    suspend fun getPoints(): List<MyPoint> {
        val response = mapPointsApi.getPoints(
            Coordinates(
                54.88324475618048,
                57.26202530166927
            )
        )
        if(response.isSuccessful){
            response.body()?.forEach { points.add(it) }
        }
        return points
    }
}