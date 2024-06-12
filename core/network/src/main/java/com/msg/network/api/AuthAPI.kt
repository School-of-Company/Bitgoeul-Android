package com.msg.network.api

import com.msg.network.request.auth.FindPasswordRequest
import com.msg.network.request.auth.LoginRequest
import com.msg.network.request.auth.SignUpBbozzakTeacherRequest
import com.msg.network.request.auth.SignUpCompanyInstructorRequest
import com.msg.network.request.auth.SignUpGovernmentRequest
import com.msg.network.request.auth.SignUpJobClubTeacherRequest
import com.msg.network.request.auth.SignUpProfessorRequest
import com.msg.network.request.auth.SignUpStudentRequest
import com.msg.network.response.auth.AuthTokenResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): AuthTokenResponse

    @POST("auth/student")
    suspend fun signUpStudent(
        @Body body: SignUpStudentRequest
    )

    @POST("auth/teacher")
    suspend fun signUpJobClubTeacher(
        @Body body: SignUpJobClubTeacherRequest
    )

    @POST("auth/professor")
    suspend fun signUpProfessor(
        @Body body: SignUpProfessorRequest
    )

    @POST("auth/government")
    suspend fun signUpGovernment(
        @Body body: SignUpGovernmentRequest
    )

    @POST("auth/company-instructor")
    suspend fun signUpCompanyInstructor(
        @Body body: SignUpCompanyInstructorRequest
    )

    @POST("auth/bbozzak")
    suspend fun signUpBbozzakTeacher(
        @Body body: SignUpBbozzakTeacherRequest
    )

    @PATCH("auth/password")
    suspend fun findPassword(
        @Body body: FindPasswordRequest // newPassword -> 8 ~ 24 영어(대문자 소문자 상관 X) + 숫자 + 특수 문자(여러 개도 상관 X)
    )

    @DELETE("auth")
    suspend fun logout()

    @DELETE("auth/withdraw")
    suspend fun withdraw()
}