package com.msg.data.repository.user

import com.msg.data.mapper.user.toEntity
import com.msg.data.mapper.user.toRequest
import com.msg.model.entity.user.InquiryMyPageEntity
import com.msg.model.param.user.ChangePasswordParam
import com.msg.network.datasource.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun changePassword(body: ChangePasswordParam): Flow<Unit> {
        return userDataSource.changePassword(
            body = body.toRequest()
        )
    }

    override fun inquiryMyPage(): Flow<InquiryMyPageEntity> {
        return userDataSource.inquiryMyPage()
            .transform { response ->
                response.toEntity()
            }
    }
}