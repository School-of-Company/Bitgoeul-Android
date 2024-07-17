package com.msg.model.entity.company

data class GetCompanyListEntity(
    val companies: List<Company>
) {
    data class Company(
        val id: Long,
        val companyName: String,
        val field: String
    )
}
