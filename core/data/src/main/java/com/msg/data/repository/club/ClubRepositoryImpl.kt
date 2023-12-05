package com.msg.data.repository.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import com.msg.network.datasource.club.ClubDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
) : ClubRepository{
    override suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>> {
        return clubDataSource.getClubList(highSchool = highSchool)
    }

    override suspend fun getClubDetail(id: Long): Flow<ClubDetailResponse> {
        return clubDataSource.getClubDetail(id = id)
    }

    override suspend fun getStudentBelongClubList(id: Long): Flow<List<StudentBelongClubResponse>> {
        return clubDataSource.getStudentBelongClubList(id = id)
    }

}