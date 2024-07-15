package com.msg.data.repository.school

import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.param.school.PostSchoolParam
import com.msg.network.request.school.PostSchoolRequest
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getSchoolList(): Flow<GetSchoolListEntity>
    fun getSchoolName(): Flow<List<String>>
    fun postSchool(body: PostSchoolParam): Flow<Unit>
}