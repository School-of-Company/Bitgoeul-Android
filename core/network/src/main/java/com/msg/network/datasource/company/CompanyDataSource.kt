package com.msg.network.datasource.company

import com.msg.network.request.company.PostCompanyRequest
import com.msg.network.response.company.GetCompanyListResponse
import kotlinx.coroutines.flow.Flow

interface CompanyDataSource {
    fun getCompanyList(): Flow<GetCompanyListResponse>
    fun postCompany(body: PostCompanyRequest): Flow<Unit>
    fun deleteCompany(id: Long): Flow<Unit>
}