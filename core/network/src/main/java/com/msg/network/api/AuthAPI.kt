package com.msg.network.api

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.model.remote.request.SignUpCompanyInstructorRequest
import com.msg.model.remote.request.SignUpGovernmentRequest
import com.msg.model.remote.request.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.SignUpProfessorRequest
import com.msg.model.remote.request.SignUpStudentRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): AuthTokenModel

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

    @DELETE("auth")
    suspend fun logout()
}