package com.msg.data.repository.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.network.datasource.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun changePassword(body: ChangePasswordRequest): Flow<Unit> {
        return userDataSource.changePassword(body = body)
    }
}