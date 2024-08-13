package com.msg.design_system.util

fun String.checkEmailRegex(): Boolean = this.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())

fun String.checkPasswordRegex(): Boolean = this.matches("^(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$".toRegex())