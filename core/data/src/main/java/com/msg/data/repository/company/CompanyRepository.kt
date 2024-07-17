package com.msg.data.repository.company

import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.param.company.PostCompanyParam
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {
    fun getCompanyList(): Flow<GetCompanyListEntity>
    fun postCompany(body: PostCompanyParam): Flow<Unit>
    fun deleteCompany(id: Long): Flow<Unit>
}