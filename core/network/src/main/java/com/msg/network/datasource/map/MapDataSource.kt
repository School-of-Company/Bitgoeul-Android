package com.msg.network.datasource.map

import com.msg.network.api.MapAPI
import com.msg.network.response.map.GetLocationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MapDataSource{
    fun getLocation(address: String): Flow<GetLocationResponse>
}