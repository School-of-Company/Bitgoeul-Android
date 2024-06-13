package com.msg.data.repository.activity

import com.msg.model.entity.activity.GetDetailStudentActivityInfoEntity
import com.msg.model.entity.activity.GetStudentActivityListEntity
import com.msg.model.model.activity.StudentActivityModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID


interface ActivityRepository {
    fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit>
    fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit>
    fun approveStudentActivityInfo(id: UUID): Flow<Unit>
    fun rejectStudentActivityInfo(id: UUID): Flow<Unit>
    fun deleteStudentActivityInfo(id: UUID): Flow<Unit>
    fun getMyStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListEntity>
    fun getStudentActivityInfoList(page: Int, size: Int, id: UUID): Flow<GetStudentActivityListEntity>
    fun getEntireStudentActivityInfoList(page: Int, size: Int): Flow<GetStudentActivityListEntity>
    fun getDetailStudentActivityInfo(id: UUID): Flow<GetDetailStudentActivityInfoEntity>
}