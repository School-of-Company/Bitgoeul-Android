package com.msg.network.datasource.club

import com.msg.network.response.club.*
import com.msg.model.enumdata.HighSchool
import com.msg.network.request.club.PatchClubRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubDataSource {
    fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    fun getStudentBelongClubDetail(id:Long, studentId: UUID): Flow<StudentBelongClubResponse>
    fun getMyClubDetail(): Flow<ClubDetailResponse>
    fun patchClub(id: Long, body: PatchClubRequest): Flow<Unit>
    fun deleteClub(id: Long): Flow<Unit>
}