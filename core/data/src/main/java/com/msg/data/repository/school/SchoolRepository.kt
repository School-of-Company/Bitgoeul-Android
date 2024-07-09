package com.msg.data.repository.school

import com.msg.model.entity.school.GetSchoolListEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getSchoolList(): Flow<GetSchoolListEntity>
}