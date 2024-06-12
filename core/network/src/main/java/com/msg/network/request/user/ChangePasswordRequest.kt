package com.msg.network.request.user

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String
)