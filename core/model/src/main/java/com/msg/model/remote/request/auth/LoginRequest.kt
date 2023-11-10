package com.msg.model.remote.request.auth

data class LoginRequest(
    val email: String,
    val password: String
)
