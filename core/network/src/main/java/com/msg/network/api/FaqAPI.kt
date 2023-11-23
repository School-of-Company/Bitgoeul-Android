package com.msg.network.api

import retrofit2.http.POST

interface FaqAPI {
    @POST("FAQ")
    suspend fun addFrequentlyAskedQuestions() {

    }
}