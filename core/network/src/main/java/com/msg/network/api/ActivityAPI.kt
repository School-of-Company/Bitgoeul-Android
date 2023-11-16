package com.msg.network.api

import com.msg.model.remote.model.activity.StudentActivityModel
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ActivityAPI {
    @POST("activity")
    suspend fun addStudentActivityInfo(
        @Body body: StudentActivityModel
    )

    @PATCH("patch/{id}")
    suspend fun editStudentActivityInfo(
        @Path("id") id: UUID,
        @Body body: StudentActivityModel
    )
}