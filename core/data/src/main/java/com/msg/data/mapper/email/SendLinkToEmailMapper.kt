package com.msg.data.mapper.email

import com.msg.model.param.email.SendLinkToEmailParam
import com.msg.network.request.email.SendLinkToEmailRequest

fun SendLinkToEmailParam.toRequest() = SendLinkToEmailRequest(
    email = email,
)