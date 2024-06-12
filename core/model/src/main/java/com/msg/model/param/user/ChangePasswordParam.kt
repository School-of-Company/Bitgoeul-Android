package com.msg.model.param.user

data class ChangePasswordParam(
    val currentPassword: String,
    val newPassword: String
)