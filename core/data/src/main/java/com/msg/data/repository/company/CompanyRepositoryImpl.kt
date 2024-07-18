package com.msg.data.repository.company

import com.msg.data.mapper.company.toEntity
import com.msg.data.mapper.company.toRequest
import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.param.company.PostCompanyParam
import com.msg.network.datasource.company.CompanyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyDataSource: CompanyDataSource
) : CompanyRepository {

    override fun getCompanyList(): Flow<GetCompanyListEntity> {
        return companyDataSource.getCompanyList().transform {response ->
            emit(response.toEntity())
        }
    }

    override fun postCompany(body: PostCompanyParam): Flow<Unit> {
        return companyDataSource.postCompany(body = body.toRequest())
    }

    override fun deleteCompany(id: Long): Flow<Unit> {
        return companyDataSource.deleteCompany(id = id)
    }
}