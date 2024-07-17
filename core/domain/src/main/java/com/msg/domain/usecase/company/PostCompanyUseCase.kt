package com.msg.domain.usecase.company

import com.msg.data.repository.company.CompanyRepository
import com.msg.model.param.company.PostCompanyParam
import javax.inject.Inject

class PostCompanyUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {
    operator fun invoke(body: PostCompanyParam) = runCatching {
        companyRepository.postCompany(body = body)
    }
}