package com.msg.network.datasource.university

import com.msg.network.request.university.PostDepartmentRequest
import com.msg.network.request.university.PostUniversityRequest
import com.msg.network.response.university.GetUniversityResponse
import kotlinx.coroutines.flow.Flow

interface UniversityDataSource {
    fun getUniversity(): Flow<GetUniversityResponse>
    fun postUniversity(body: PostUniversityRequest): Flow<Unit>
    fun patchUniversity(id: Long, body: PostUniversityRequest): Flow<Unit>
    fun deleteUniversity(id: Long): Flow<Unit>
    fun postDepartment(body: PostDepartmentRequest): Flow<Unit>
    fun deleteDepartment(id: Long, department: String): Flow<Unit>
}