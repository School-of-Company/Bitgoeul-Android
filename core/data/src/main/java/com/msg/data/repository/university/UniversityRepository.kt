package com.msg.data.repository.university

import com.msg.model.entity.university.GetUniversityEntity
import com.msg.model.param.university.PostDepartmentParam
import com.msg.model.param.university.PostUniversityParam
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversity(): Flow<GetUniversityEntity>
    fun postUniversity(body: PostUniversityParam): Flow<Unit>
    fun patchUniversity(id: Long, body: PostUniversityParam): Flow<Unit>
    fun deleteUniversity(id: Long): Flow<Unit>
    fun postDepartment(body: PostDepartmentParam): Flow<Unit>
    fun deleteDepartment(id: Long, department: String): Flow<Unit>
}