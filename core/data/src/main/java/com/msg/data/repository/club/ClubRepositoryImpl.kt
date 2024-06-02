package com.msg.data.repository.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import com.msg.network.datasource.club.ClubDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
) : ClubRepository{
    override fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>> {
        return clubDataSource.getClubList(highSchool = highSchool)
    }

    override fun getClubDetail(id: Long): Flow<ClubDetailResponse> {
        return clubDataSource.getClubDetail(id = id)
    }

    override fun getStudentBelongClubDetail(
        id: Long,
        studentId: UUID
    ): Flow<StudentBelongClubResponse> {
        return clubDataSource.getStudentBelongClubDetail(id = id, studentId = studentId)
    }

    override fun getMyClubDetail(): Flow<ClubDetailResponse> {
        return clubDataSource.getMyClubDetail()
    }
}