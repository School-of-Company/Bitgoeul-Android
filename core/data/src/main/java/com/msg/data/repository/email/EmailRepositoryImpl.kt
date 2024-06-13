package com.msg.data.repository.email

import com.msg.data.mapper.email.toEntity
import com.msg.data.mapper.email.toRequest
import com.msg.model.entity.email.GetEmailAuthenticateStatusEntity
import com.msg.model.param.email.SendLinkToEmailParam
import com.msg.network.datasource.email.EmailDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val emailDataSource: EmailDataSource,
) : EmailRepository {
    override fun sendLinkToEmail(body: SendLinkToEmailParam): Flow<Unit> {
        return emailDataSource.sendLinkToEmail(
            body = body.toRequest()
        )
    }

    override fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusEntity> {
        return emailDataSource.getEmailAuthenticateStatus(
            email = email
        ).transform { response ->
            response.toEntity()
        }
    }
}