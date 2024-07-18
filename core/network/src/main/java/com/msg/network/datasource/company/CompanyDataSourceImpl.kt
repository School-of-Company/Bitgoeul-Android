package com.msg.network.datasource.company

import com.msg.network.api.CompanyAPI
import com.msg.network.request.company.PostCompanyRequest
import com.msg.network.response.company.GetCompanyListResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompanyDataSourceImpl @Inject constructor(
    private val companyAPI: CompanyAPI
) : CompanyDataSource {

    override fun getCompanyList(): Flow<GetCompanyListResponse> =
        makeRequest { companyAPI.getCompanyList() }

    override fun postCompany(body: PostCompanyRequest): Flow<Unit> =
        makeRequest { companyAPI.postCompany(body = body) }

    override fun deleteCompany(id: Long): Flow<Unit> =
        makeRequest { companyAPI.deleteCompany(id = id) }
}