package com.msg.network.api

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface FaqAPI {
    @POST("FAQ")
    suspend fun addFrequentlyAskedQuestions(
        @Body body: AddFrequentlyAskedQuestionsRequest,
    )

    @GET("FAQ/{id}")
    suspend fun getFrequentlyAskedQuestionsDetail(
        @Path("id") id: UUID,
    )

}