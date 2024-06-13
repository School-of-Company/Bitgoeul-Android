package com.msg.network.datasource.club

import com.msg.network.response.club.*
import com.msg.model.enumdata.HighSchool
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubDataSource {
    fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    fun getStudentBelongClubDetail(id:Long, studentId: UUID): Flow<StudentBelongClubResponse>
    fun getMyClubDetail(): Flow<ClubDetailResponse>
}