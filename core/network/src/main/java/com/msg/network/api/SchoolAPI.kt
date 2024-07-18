package com.msg.network.api

import com.msg.network.request.school.PatchSchoolRequest
import com.msg.network.request.school.PostSchoolRequest
import com.msg.network.response.school.GetSchoolListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface SchoolAPI {
    @GET("school")
    suspend fun getSchoolList(): GetSchoolListResponse

    @GET("school/name")
    suspend fun getSchoolName(): List<String>

    @Multipart
    @POST("school")
    suspend fun postSchool(
        @Body body: PostSchoolRequest
    )

    @Multipart
    @PATCH("school/{id}")
    suspend fun patchSchool(
        @Path("id") id: Long,
        @Body body: PatchSchoolRequest
    )

    @DELETE("school/{id}")
    suspend fun deleteSchool(
        @Path("id") id: Long
    )
}