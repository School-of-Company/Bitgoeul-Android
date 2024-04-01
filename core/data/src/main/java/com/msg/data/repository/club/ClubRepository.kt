package com.msg.data.repository.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubRepository {
    suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    suspend fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    suspend fun getStudentBelongClubDetail(id: Long, studentId: UUID): Flow<StudentBelongClubResponse>
    suspend fun getMyClubDetail(): Flow<ClubDetailResponse>
}