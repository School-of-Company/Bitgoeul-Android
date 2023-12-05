package com.msg.network.api

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClubAPI {
    @GET("club")
    suspend fun getClubList(
        @Query("highschool") highSchool: HighSchool,
    ): List<ClubListResponse>

    @GET("club/{id}")
    suspend fun getClubDetail(
        @Path("id") id: Long,
    ): ClubDetailResponse

    @GET("club/{id}/member")
    suspend fun getStudentBelongClubList(
        @Path("id") id: Long,
    ): List<StudentBelongClubResponse>
}