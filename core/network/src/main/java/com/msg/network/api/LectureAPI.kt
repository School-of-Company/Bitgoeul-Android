package com.msg.network.api

import com.msg.model.remote.model.LectureListModel
import com.msg.model.remote.request.lecture.OpenLectureRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface LectureAPI {
    @POST("/lecture")
    suspend fun openLecture(
        @Body openLectureRequest: OpenLectureRequest,
    )

    @GET("/lecture")
    suspend fun getLectureList(): LectureListModel

    @GET("/lecture/{id}")
    suspend fun getDetailLecture(
        @Path("id") id: UUID,
    )
}