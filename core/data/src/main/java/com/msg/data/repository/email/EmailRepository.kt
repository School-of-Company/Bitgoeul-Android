package com.msg.data.repository.email

import com.msg.model.remote.request.email.SendLinkToEmailRequest
import com.msg.model.remote.response.email.GetEmailAuthenticateStatusResponse
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    fun sendLinkToEmail(body: SendLinkToEmailRequest): Flow<Unit>
    fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusResponse>
}