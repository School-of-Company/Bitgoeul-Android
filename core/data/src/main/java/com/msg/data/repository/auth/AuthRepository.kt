package com.msg.data.repository.auth

import com.msg.model.entity.auth.AuthTokenEntity
import com.msg.model.entity.auth.TokenAccessEntity
import com.msg.model.enumdata.Authority
import com.msg.model.param.auth.FindPasswordParam
import com.msg.model.param.auth.LoginParam
import com.msg.model.param.auth.SignUpBbozzakTeacherParam
import com.msg.model.param.auth.SignUpCompanyInstructorParam
import com.msg.model.param.auth.SignUpGovernmentParam
import com.msg.model.param.auth.SignUpJobClubTeacherParam
import com.msg.model.param.auth.SignUpProfessorParam
import com.msg.model.param.auth.SignUpStudentParam
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(body: LoginParam): Flow<AuthTokenEntity>
    fun signUpStudent(body: SignUpStudentParam): Flow<Unit>
    fun signUpJobClubTeacher(body: SignUpJobClubTeacherParam): Flow<Unit>
    fun signUpProfessor(body: SignUpProfessorParam): Flow<Unit>
    fun signUpGovernment(body: SignUpGovernmentParam): Flow<Unit>
    fun signUpCompanyInstructor(body: SignUpCompanyInstructorParam): Flow<Unit>
    fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherParam): Flow<Unit>
    fun findPassword(body: FindPasswordParam): Flow<Unit>
    fun logout(): Flow<Unit>
    fun withdraw(): Flow<Unit>
    fun saveToken(data: AuthTokenEntity): Flow<Unit>
    fun getAuthority(): Flow<Authority>
    fun getTokenAccess(): Flow<String>
    fun tokenAccess(): Flow<TokenAccessEntity>
}