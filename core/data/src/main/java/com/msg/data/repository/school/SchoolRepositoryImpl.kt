package com.msg.data.repository.school

import com.msg.data.mapper.school.toEntity
import com.msg.data.mapper.school.toRequest
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.param.school.PostSchoolParam
import com.msg.network.datasource.school.SchoolDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolDataSource: SchoolDataSource
) : SchoolRepository {
    override fun getSchoolList(): Flow<GetSchoolListEntity> {
        return schoolDataSource.getSchoolList().transform { response ->
            response.toEntity()
        }
    }

    override fun getSchoolName(): Flow<List<String>> {
        return schoolDataSource.getSchoolName()
    }

    override fun postSchool(body: PostSchoolParam): Flow<Unit> {
        return schoolDataSource.postSchool(body = body.toRequest())
    }
}