package com.msg.network.datasource.club

import com.msg.model.enumdata.HighSchool
import com.msg.network.response.club.ClubDetailResponse
import com.msg.network.response.club.ClubListResponse
import com.msg.network.response.club.StudentBelongClubResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubDataSource {
    fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    fun getStudentBelongClubDetail(id:Long, studentId: UUID): Flow<StudentBelongClubResponse>
    fun getMyClubDetail(): Flow<ClubDetailResponse>
}