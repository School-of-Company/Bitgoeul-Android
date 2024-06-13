package com.msg.data.mapper.auth

import com.msg.model.param.auth.FindPasswordParam
import com.msg.network.request.auth.FindPasswordRequest

fun FindPasswordParam.toRequest() = FindPasswordRequest(
    email = email,
    newPassword = newPassword,
)