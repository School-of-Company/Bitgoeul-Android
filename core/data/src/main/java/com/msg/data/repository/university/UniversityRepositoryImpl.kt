package com.msg.data.repository.university

import com.msg.data.mapper.university.toEntity
import com.msg.data.mapper.university.toRequest
import com.msg.model.entity.university.GetUniversityEntity
import com.msg.model.param.university.PostDepartmentParam
import com.msg.model.param.university.PostUniversityParam
import com.msg.network.datasource.university.UniversityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityDataSource: UniversityDataSource
) : UniversityRepository {

    override fun getUniversity(): Flow<GetUniversityEntity> {
        return universityDataSource.getUniversity().transform {
            it.toEntity()
        }
    }

    override fun postUniversity(body: PostUniversityParam): Flow<Unit> {
        return universityDataSource.postUniversity(body = body.toRequest())
    }

    override fun patchUniversity(id: Long, body: PostUniversityParam): Flow<Unit> {
        return universityDataSource.patchUniversity(id = id, body = body.toRequest())
    }

    override fun deleteUniversity(id: Long): Flow<Unit> {
        return universityDataSource.deleteUniversity(id = id)
    }

    override fun postDepartment(body: PostDepartmentParam): Flow<Unit> {
        return universityDataSource.postDepartment(body = body.toRequest())
    }

    override fun deleteDepartment(id: Long, department: String): Flow<Unit> {
        return universityDataSource.deleteDepartment(id = id, department = department)
    }
}