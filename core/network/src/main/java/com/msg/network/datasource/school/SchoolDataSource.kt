package com.msg.network.datasource.school

import com.msg.network.request.school.PatchSchoolRequest
import com.msg.network.request.school.PostSchoolRequest
import com.msg.network.response.school.GetSchoolListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface SchoolDataSource {
    fun getSchoolList(): Flow<GetSchoolListResponse>
    fun getSchoolName(): Flow<List<String>>
    fun postSchool(body: PostSchoolRequest): Flow<Unit>
    fun patchSchool(id: Long, body: PatchSchoolRequest): Flow<Unit>
    fun deleteSchool(id: Long): Flow<Unit>
}