package com.msg.domain.usecase.company

import com.msg.data.repository.company.CompanyRepository
import javax.inject.Inject

class DeleteCompanyUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {
    operator fun invoke(id: Long) = runCatching {
        companyRepository.deleteCompany(id = id)
    }
}