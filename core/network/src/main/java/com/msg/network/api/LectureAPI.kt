package com.msg.network.api

import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.DownloadExcelFileResponse
import com.msg.model.remote.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.model.remote.response.lecture.GetTakingLectureStudentListResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchDivisionResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
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
        @Query("type") type: String?,
    ): LectureListResponse

    @GET("lecture/{id}")
    suspend fun getDetailLecture(
        @Path("id") id: UUID,
    ): DetailLectureResponse

    @POST("lecture/{id}")
    suspend fun lectureApplication(
        @Path("id") id: UUID,
    )

    @DELETE("lecture/{id}")
    suspend fun lectureApplicationCancel(
        @Path("id") id: UUID,
    )

    @GET("lecture/instructor")
    suspend fun searchProfessor(
        @Query("keyword") keyword: String,
    ): SearchProfessorResponse


    @GET("lecture/line")
    suspend fun searchLine(
        @Query("keyword") keyword: String,
        @Query("division") division: String,
    ): SearchLineResponse

    @GET("lecture/department")
    suspend fun searchDepartment(
        @Query("keyword") keyword: String,
    ): SearchDepartmentResponse

    @GET("lecture/division")
    suspend fun searchDivision(
        @Query("keyword") keyword: String,
    ): SearchDivisionResponse

    @GET("lecture/{student_id}}/signup")
    suspend fun getLectureSignUpHistory(
        @Path("student_id") studentId: UUID,
    ): GetLectureSignUpHistoryResponse

    @GET("lecture/student/{id}")
    suspend fun getTakingLectureStudentList(
        @Path("id") id: UUID,
    ): GetTakingLectureStudentListResponse

    @PATCH("{id}/{student_id}")
    suspend fun editLectureCourseCompletionStatus(
        @Path("id") id: UUID,
        @Path("student_id") studentId: UUID,
        @Query("isComplete") isComplete: Boolean,
    )

    @GET("lecture/excel")
    suspend fun downloadExcelFile(): DownloadExcelFileResponse
}