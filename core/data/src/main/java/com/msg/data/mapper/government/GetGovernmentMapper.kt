package com.msg.data.mapper.government

import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.network.response.government.GetGovernmentResponse
import com.msg.model.entity.government.GetGovernmentEntity.Government as DomainGovernment
import com.msg.network.response.government.GetGovernmentResponse.Government

fun GetGovernmentResponse.toEntity() = GetGovernmentEntity(
    governments = governments.map { it.toDomainGovernment() }
)

fun Government.toDomainGovernment() = DomainGovernment(
    id = id,
    field = field,
    governmentName = governmentName
)