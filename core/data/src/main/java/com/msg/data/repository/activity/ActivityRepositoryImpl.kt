package com.msg.data.repository.activity

import com.msg.data.mapper.activity.toEntity
import com.msg.model.entity.activity.GetDetailStudentActivityInfoEntity
import com.msg.model.entity.activity.GetStudentActivityListEntity
import com.msg.model.model.activity.StudentActivityModel
import com.msg.network.datasource.activity.ActivityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityDataSource: ActivityDataSource
) : ActivityRepository {
    override fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.addStudentActivityInfo(
            body = body
        )
    }

    override fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> {
        return activityDataSource.editStudentActivityInfo(
            id = id,
            body = body
        )
    }

    override fun approveStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.approveStudentActivityInfo(
            id = id
        )
    }

    override fun rejectStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.rejectStudentActivityInfo(
            id = id
        )
    }

    override fun deleteStudentActivityInfo(id: UUID): Flow<Unit> {
        return activityDataSource.deleteStudentActivityInfo(
            id = id
        )
    }

    override fun getMyStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListEntity> {
        return activityDataSource.getMyStudentActivityInfoList(
            page = page,
            size = size,
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getStudentActivityInfoList(
        page: Int,
        size: Int,
        id: UUID
    ): Flow<GetStudentActivityListEntity> {
        return activityDataSource.getStudentActivityInfoList(
            page = page,
            size = size,
            id = id
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getEntireStudentActivityInfoList(
        page: Int,
        size: Int,
    ): Flow<GetStudentActivityListEntity> {
        return activityDataSource.getEntireStudentActivityInfoList(
            page = page,
            size = size,
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoEntity> {
        return activityDataSource.getDetailStudentActivityInfo(
            id = id
        ).transform { response ->
            response.toEntity()
        }
    }
}