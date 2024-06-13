package com.msg.data.repository.admin

import com.msg.model.entity.admin.UserListEntity
import com.msg.model.param.admin.GetUserListParam
import kotlinx.coroutines.flow.Flow

interface AdminRepository {
    fun getUserList(body: GetUserListParam, keyword: String): Flow<UserListEntity>
}