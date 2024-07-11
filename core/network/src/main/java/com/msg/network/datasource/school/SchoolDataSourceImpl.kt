package com.msg.network.datasource.school

import com.msg.network.api.SchoolAPI
import com.msg.network.request.school.PostSchoolRequest
import com.msg.network.response.school.GetSchoolListResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolDataSourceImpl @Inject constructor(
    private val schoolAPI: SchoolAPI
) : SchoolDataSource {
    override fun getSchoolList(): Flow<GetSchoolListResponse> =
        makeRequest { schoolAPI.getSchoolList() }

    override fun getSchoolName(): Flow<List<String>> =
        makeRequest { schoolAPI.getSchoolName() }

    override fun postSchool(body: PostSchoolRequest): Flow<Unit> =
        makeRequest { schoolAPI.postSchool(body = body) }
}