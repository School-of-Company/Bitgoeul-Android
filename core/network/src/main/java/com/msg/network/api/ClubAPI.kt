package com.msg.network.api

import com.msg.model.enumdata.HighSchool
import com.msg.network.response.club.ClubDetailResponse
import com.msg.network.response.club.ClubListResponse
import com.msg.network.response.club.StudentBelongClubResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ClubAPI {
    @GET("club")
    suspend fun getClubList(
        @Query("highschool") highSchool: HighSchool,
    ): List<ClubListResponse>

    @GET("club/{id}")
    suspend fun getClubDetail(
        @Path("id") id: Long,
    ): ClubDetailResponse

    @GET("club/{id}/{student_id}")
    suspend fun getStudentBelongClubDetail(
        @Path("id") id: Long,
        @Path("student_id") studentId: UUID,
    ): StudentBelongClubResponse

    @GET("club/my")
    suspend fun getMyClubDetail(): ClubDetailResponse
}