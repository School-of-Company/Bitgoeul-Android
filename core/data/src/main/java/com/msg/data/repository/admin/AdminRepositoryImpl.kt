package com.msg.data.repository.admin

import com.msg.data.mapper.admin.toEntity
import com.msg.data.mapper.admin.toRequest
import com.msg.model.entity.admin.UserListEntity
import com.msg.model.param.admin.GetUserListParam
import com.msg.network.datasource.admin.AdminDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminDataSource: AdminDataSource,
) : AdminRepository {
    override fun getUserList(
        body: GetUserListParam,
        keyword: String,
    ): Flow<UserListEntity> {
        return adminDataSource.getUserList(
            body = body.toRequest(),
            keyword = keyword
        ).transform { response ->
            response.toEntity()
        }
    }
}