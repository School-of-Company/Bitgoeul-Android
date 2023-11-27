package com.msg.network.api

import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface LectureAPI {
    @POST("lecture")
    suspend fun openLecture(
        @Body body: OpenLectureRequest,
    )

    @GET("lecture")
    suspend fun getLectureList(): List<LectureListResponse>

    @GET("lecture/{id}")
    suspend fun getDetailLecture(
        @Path("id") id: UUID,
    ): DetailLectureResponse

    @POST("lecture/{id}")
    suspend fun lectureApplication(
        @Path("id") id: UUID,
    )

    @PATCH("lecture/{id}/approve")
    suspend fun approvePendingLecture(
        @Path("id") id: UUID,
    )

    @DELETE("lecture/{id}/reject")
    suspend fun rejectPendingLecture(
        @Path("id") id: UUID,
    )
}