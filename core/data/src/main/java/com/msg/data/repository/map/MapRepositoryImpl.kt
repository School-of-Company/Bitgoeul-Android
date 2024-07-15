package com.msg.data.repository.map

import com.msg.data.mapper.map.toEntity
import com.msg.model.entity.map.GetLocationEntity
import com.msg.network.datasource.map.MapDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapDataSource: MapDataSource
) : MapRepository {
    override fun getLocation(address: String): Flow<GetLocationEntity> {
        return mapDataSource.getLocation(address = address).transform {
            it.toEntity()
        }
    }
}