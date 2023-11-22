package com.msg.design_system.util

fun String.checkEmailRegex(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return this.matches(emailRegex.toRegex())
}

fun String.checkPasswordRegex(): Boolean {
    val passwordRegex = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!?.])(?=\\S+$).{8,24}.$"
    return this.matches(passwordRegex.toRegex())
}