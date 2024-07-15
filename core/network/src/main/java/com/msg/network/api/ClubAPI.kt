package com.msg.network.api

import com.msg.model.enumdata.HighSchool
import com.msg.network.request.club.PatchClubRequest
import com.msg.network.request.club.PostClubRequest
import com.msg.network.response.club.ClubDetailResponse
import com.msg.network.response.club.ClubListResponse
import com.msg.network.response.club.StudentBelongClubResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ClubAPI {
    @GET("club")
    suspend fun getClubList(
        @Query("highschool") highSchool: String,
    ): List<ClubListResponse>

    @GET("club/name")
    suspend fun getClubListForSignUp(
        @Query("highschool") highSchool: String
    ): List<String>

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

    @POST("club/{school_id}")
    suspend fun postClub(
        @Path("school_id") schoolId: UUID,
        @Body body: PostClubRequest
    )

    @PATCH("club/{id}")
    suspend fun patchClub(
        @Path("id") id: Long,
        @Body body: PatchClubRequest
    )

    @DELETE("club/{id}")
    suspend fun deleteClub(
        @Path("id") id: Long
    )
}