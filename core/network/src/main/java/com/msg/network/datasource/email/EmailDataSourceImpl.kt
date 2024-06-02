package com.msg.network.datasource.email

import com.msg.model.remote.request.email.SendLinkToEmailRequest
import com.msg.model.remote.response.email.GetEmailAuthenticateStatusResponse
import com.msg.network.api.EmailAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EmailDataSourceImpl @Inject constructor(
    private val emailAPI: EmailAPI
) : EmailDataSource {
    override fun sendLinkToEmail(body: SendLinkToEmailRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { emailAPI.sendLinkToEmail(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun getEmailAuthenticateStatus(email: String): Flow<GetEmailAuthenticateStatusResponse> = flow {
        emit(
            BitgoeulApiHandler<GetEmailAuthenticateStatusResponse>()
                .httpRequest { emailAPI.getEmailAuthenticateStatus(email = email) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}