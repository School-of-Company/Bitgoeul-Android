package com.msg.network.api

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FaqAPI {
    @POST("FAQ")
    suspend fun addFrequentlyAskedQuestions(
        @Body body: AddFrequentlyAskedQuestionsRequest,
    )

}