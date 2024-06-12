package com.msg.network.datasource.email

import com.msg.network.request.email.SendLinkToEmailRequest
import com.msg.network.response.email.GetEmailAuthenticateStatusResponse
import kotlinx.coroutines.flow.Flow

interface EmailDataSource {
    fun sendLinkToEmail(body: SendLinkToEmailRequest): Flow<Unit>
    fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusResponse>
}