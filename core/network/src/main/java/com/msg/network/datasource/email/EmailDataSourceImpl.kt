package com.msg.network.datasource.email

import com.msg.network.api.EmailAPI
import com.msg.network.request.email.SendLinkToEmailRequest
import com.msg.network.response.email.GetEmailAuthenticateStatusResponse
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailDataSourceImpl @Inject constructor(
    private val emailAPI: EmailAPI
) : EmailDataSource {
    override fun sendLinkToEmail(body: SendLinkToEmailRequest): Flow<Unit> =
        makeRequest { emailAPI.sendLinkToEmail(body = body) }

    override fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusResponse> =
        makeRequest { emailAPI.getEmailAuthenticateStatus(email = email) }
}