package com.msg.data.repository.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubListResponse
import kotlinx.coroutines.flow.Flow

interface ClubRepository {
    suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
}