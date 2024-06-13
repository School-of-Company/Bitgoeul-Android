package com.msg.data.repository.email

import com.msg.model.entity.email.GetEmailAuthenticateStatusEntity
import com.msg.model.param.email.SendLinkToEmailParam
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    fun sendLinkToEmail(body: SendLinkToEmailParam): Flow<Unit>
    fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusEntity>
}