package com.msg.network.api

import com.msg.network.request.university.PostDepartmentRequest
import com.msg.network.request.university.PostUniversityRequest
import com.msg.network.response.university.GetUniversityResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UniversityAPI {

    @GET("university")
    suspend fun getUniversity(): GetUniversityResponse

    @POST("university")
    suspend fun postUniversity(
        @Body body: PostUniversityRequest
    )

    @PATCH("university/{id}")
    suspend fun patchUniversity(
        @Path("id") id: Long,
        @Body body: PostUniversityRequest
    )

    @DELETE("university/{id}")
    suspend fun deleteUniversity(
        @Path("id") id: Long
    )

    @POST("university/department/{id}")
    suspend fun postDepartment(
        @Body body: PostDepartmentRequest
    )

    @DELETE("university/department/{id}")
    suspend fun deleteDepartment(
        @Path("id") id: Long,
        @Query("department") department: String
    )
}