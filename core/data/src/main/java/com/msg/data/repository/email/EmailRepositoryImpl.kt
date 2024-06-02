package com.msg.data.repository.email

import com.msg.model.remote.request.email.SendLinkToEmailRequest
import com.msg.model.remote.response.email.GetEmailAuthenticateStatusResponse
import com.msg.network.datasource.email.EmailDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val emailDataSource: EmailDataSource
) : EmailRepository {
    override fun sendLinkToEmail(body: SendLinkToEmailRequest): Flow<Unit> {
        return emailDataSource.sendLinkToEmail(
            body = body
        )
    }

    override fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusResponse> {
        return emailDataSource.getEmailAuthenticateStatus(
            email = email
        )
    }
}