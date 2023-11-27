package com.msg.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface CertificationAPI {
    @GET("certification/{student_id}")
    suspend fun getCertificationList(
        @Path("student_id") studentId: UUID
    )
}