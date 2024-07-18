package com.msg.data.mapper.company

import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.entity.company.GetCompanyListEntity.Company as DomainCompany
import com.msg.network.response.company.GetCompanyListResponse

fun GetCompanyListResponse.toEntity() = GetCompanyListEntity(
    companies = companies.map { it.toDomainCompany() }
)

fun GetCompanyListResponse.Company.toDomainCompany() = DomainCompany(
    id = id,
    companyName = companyName,
    field = field
)