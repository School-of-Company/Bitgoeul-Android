package com.msg.domain.admin

import com.msg.data.repository.admin.AdminRepository
import com.msg.model.remote.request.admin.GetUserListRequest
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val adminRepository: AdminRepository
) {
    suspend operator fun invoke(body: GetUserListRequest, keyword: String) = kotlin.runCatching {
        adminRepository.getUserList(body = body, keyword = keyword)
    }
}