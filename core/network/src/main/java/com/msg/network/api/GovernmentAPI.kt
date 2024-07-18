package com.msg.network.api

import com.msg.network.request.government.PostGovernmentRequest
import com.msg.network.response.government.GetGovernmentResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GovernmentAPI {

    @GET("government")
    suspend fun getGovernment(): GetGovernmentResponse

    @POST("government")
    suspend fun postGovernment(
        @Body body: PostGovernmentRequest
    )

    @DELETE("government/{id}")
    suspend fun deleteGovernment(
        @Path("id") id: Long
    )
}