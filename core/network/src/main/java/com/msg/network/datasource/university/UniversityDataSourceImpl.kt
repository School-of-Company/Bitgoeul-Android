package com.msg.network.datasource.university

import com.msg.network.api.UniversityAPI
import com.msg.network.request.university.PostDepartmentRequest
import com.msg.network.request.university.PostUniversityRequest
import com.msg.network.response.university.GetUniversityResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversityDataSourceImpl @Inject constructor(
    private val universityAPI: UniversityAPI
) : UniversityDataSource {

    override fun getUniversity(): Flow<GetUniversityResponse> =
        makeRequest { universityAPI.getUniversity() }

    override fun postUniversity(body: PostUniversityRequest): Flow<Unit> =
        makeRequest { universityAPI.postUniversity(body = body) }

    override fun patchUniversity(id: Long, body: PostUniversityRequest): Flow<Unit> =
        makeRequest { universityAPI.patchUniversity(id = id, body = body) }

    override fun deleteUniversity(id: Long): Flow<Unit> =
        makeRequest { universityAPI.deleteUniversity(id = id) }

    override fun postDepartment(body: PostDepartmentRequest): Flow<Unit> =
        makeRequest { universityAPI.postDepartment(body = body) }

    override fun deleteDepartment(id: Long, department: String): Flow<Unit> =
        makeRequest { universityAPI.deleteDepartment(id = id, department = department) }
}