package com.msg.network.datasource.map

import com.msg.network.api.MapAPI
import com.msg.network.response.map.GetLocationResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MapDataSourceImpl @Inject constructor(
    private val mapAPI: MapAPI
) : MapDataSource {
    override fun getLocation(address: String): Flow<GetLocationResponse> =
        makeRequest { mapAPI.getLocation(address = address) }
}