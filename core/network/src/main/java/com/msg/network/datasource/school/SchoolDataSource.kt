package com.msg.network.datasource.school

import com.msg.network.request.school.PostSchoolRequest
import com.msg.network.response.school.GetSchoolListResponse
import kotlinx.coroutines.flow.Flow

interface SchoolDataSource {
    fun getSchoolList(): Flow<GetSchoolListResponse>
    fun getSchoolName(): Flow<List<String>>
    fun postSchool(body: PostSchoolRequest): Flow<Unit>
}