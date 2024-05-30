package com.msg.network.api

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
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
    suspend fun getMyStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetStudentActivityListResponse

    @GET("activity/{student_id}")
    suspend fun getStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Path("student_id") id: UUID
    ): GetStudentActivityListResponse

    @GET("activity")
    suspend fun getEntireStudentActivityInfoList(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetStudentActivityListResponse

    @GET("activity/{id}/detail")
    suspend fun getDetailStudentActivityInfo(
        @Path("id") id: UUID
    ): GetDetailStudentActivityInfoResponse
}