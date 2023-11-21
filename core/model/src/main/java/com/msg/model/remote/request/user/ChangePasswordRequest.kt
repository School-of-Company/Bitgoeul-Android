package com.msg.model.remote.request.user

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String
)