package com.msg.network.api

import com.msg.network.response.map.GetLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MapAPI {
    @GET("map")
    suspend fun getLocation(
        @Query("address") address: String
    ): GetLocationResponse
}