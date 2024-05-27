package com.msg.network.api

import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CertificationAPI {
    @GET("certification/{student_id}")
    suspend fun getCertificationListForTeacher(
        @Path("student_id") studentId: UUID
    ): List<CertificationListResponse>

    @GET("certification")
    suspend fun getCertificationListForStudent(): List<CertificationListResponse>

    @POST("certification/")
    suspend fun writeCertification(
        @Body body: WriteCertificationRequest,
    )

    @PATCH("certification/{id}")
    suspend fun editCertification(
        @Path("id") id: UUID,
        @Body body: WriteCertificationRequest
    )
}