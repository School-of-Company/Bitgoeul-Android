package com.msg.data.repository.school

import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.param.school.PatchSchoolParam
import com.msg.model.param.school.PostSchoolParam
import com.msg.network.request.school.PostSchoolRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface SchoolRepository {
    fun getSchoolList(): Flow<GetSchoolListEntity>
    fun getSchoolName(): Flow<List<String>>
    fun postSchool(body: PostSchoolParam): Flow<Unit>
    fun patchSchool(id: Long, body: PatchSchoolParam): Flow<Unit>
    fun deleteSchool(id: Long): Flow<Unit>
}