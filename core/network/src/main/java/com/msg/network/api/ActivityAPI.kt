package com.msg.network.api

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ActivityAPI {
    @POST("activity")
    suspend fun addStudentActivityInfo(
        @Body body: StudentActivityModel
    )

    @PATCH("activity/{id}")
    suspend fun editStudentActivityInfo(
        @Path("id") id: UUID,
        @Body body: StudentActivityModel
    )

    @PATCH("activity/{id}/approve")
    suspend fun approveStudentActivityInfo(
        @Path("id") id: UUID
    )

    @DELETE("activity/{id}/reject")
    suspend fun rejectStudentActivityInfo(
        @Path("id") id: UUID
    )

    @DELETE("activity/{id}")
    suspend fun deleteStudentActivityInfo(
        @Path("id") id: UUID
    )

    @GET("activity/my")
    suspend fun inquiryMyStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): InquiryStudentActivityListResponse

    @GET("activity/{student_id}")
    suspend fun inquiryStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String,
        @Path("student_id") id: UUID
    ): InquiryStudentActivityListResponse

    @GET("activity")
    suspend fun inquiryEntireStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): InquiryStudentActivityListResponse
}