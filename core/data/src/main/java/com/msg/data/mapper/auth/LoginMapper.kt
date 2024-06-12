package com.msg.data.mapper.auth

import com.msg.model.param.auth.LoginParam
import com.msg.network.request.auth.LoginRequest

fun LoginParam.toRequest() = LoginRequest(
    email = email,
    password = password,
)