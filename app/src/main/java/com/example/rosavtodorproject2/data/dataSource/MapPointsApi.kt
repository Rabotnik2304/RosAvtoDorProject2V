package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.MyPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapPointsApi {
    @GET("verifiedPoints")
    suspend fun getPoints(
        @Query("params") params: Coordinates
    ): Response<List<MyPoint>>
}