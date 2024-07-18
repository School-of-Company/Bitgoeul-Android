package com.msg.domain.usecase.company

import com.msg.data.repository.company.CompanyRepository
import javax.inject.Inject

class GetCompanyListUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {
    operator fun invoke() = runCatching {
        companyRepository.getCompanyList()
    }
}