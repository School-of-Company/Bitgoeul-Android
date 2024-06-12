package com.msg.network.api

import com.msg.network.request.email.SendLinkToEmailRequest
import com.msg.network.response.email.GetEmailAuthenticateStatusResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmailAPI {
    @POST("email")
    suspend fun sendLinkToEmail(
        @Body body: SendLinkToEmailRequest
    )

    @GET("email")
    suspend fun getEmailAuthenticateStatus(
        @Query("email") email: String
    ): GetEmailAuthenticateStatusResponse
}