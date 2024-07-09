package com.msg.network.api

import com.msg.network.response.school.GetSchoolListResponse
import retrofit2.http.GET

interface SchoolAPI {
    @GET("school")
    suspend fun getSchoolList(): GetSchoolListResponse
}