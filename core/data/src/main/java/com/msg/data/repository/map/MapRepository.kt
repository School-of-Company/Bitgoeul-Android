package com.msg.data.repository.map

import com.msg.model.entity.map.GetLocationEntity
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    fun getLocation(address: String): Flow<GetLocationEntity>
}