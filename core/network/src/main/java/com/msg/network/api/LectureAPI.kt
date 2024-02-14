package com.msg.network.api

import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface LectureAPI {
    @POST("lecture")
    suspend fun openLecture(
        @Body body: OpenLectureRequest,
    )

    @GET("lecture")
    suspend fun getLectureList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("status") status: ApproveStatus,
<<<<<<< HEAD
        @Query("type") type: LectureType
=======
        @Query("type") type: LectureType,
>>>>>>> 8751c3f9e0738aa7bd07d4176a4d202d41215349
    ): List<LectureListResponse>

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