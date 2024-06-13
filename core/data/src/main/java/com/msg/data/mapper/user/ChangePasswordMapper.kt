package com.msg.data.mapper.user

import com.msg.model.param.user.ChangePasswordParam
import com.msg.network.request.user.ChangePasswordRequest

fun ChangePasswordParam.toRequest() = ChangePasswordRequest(
    currentPassword = currentPassword,
    newPassword = newPassword,
)