package com.msg.network.datasource.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubDataSource {
    fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    fun getStudentBelongClubDetail(id:Long, studentId: UUID): Flow<StudentBelongClubResponse>
    fun getMyClubDetail(): Flow<ClubDetailResponse>
}