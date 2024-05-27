package com.msg.network.api

import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FaqAPI {
    @POST("faq")
    suspend fun addFrequentlyAskedQuestions(
        @Body body: AddFrequentlyAskedQuestionsRequest,
    )

    @GET("faq")
    suspend fun getFrequentlyAskedQuestionsList(): List<GetFrequentlyAskedQuestionDetailResponse>
}