package com.msg.domain.usecase.admin

import com.msg.data.repository.admin.AdminRepository
import com.msg.model.param.admin.GetUserListParam
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val adminRepository: AdminRepository
) {
    suspend operator fun invoke(body: GetUserListParam, keyword: String) = kotlin.runCatching {
        adminRepository.getUserList(body = body, keyword = keyword)
    }
}