package com.msg.network.datasource.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.network.api.ClubAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ClubDataSourceImpl @Inject constructor(
    private val clubAPI: ClubAPI,
) : ClubDataSource {
    override suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>> = flow {
        emit(
            BitgoeulApiHandler<List<ClubListResponse>>()
                .httpRequest { clubAPI.getClubList(highSchool = highSchool) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}