package com.bitgoeul.email.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val inputEmailRoute = "input_email_route"

fun NavController.navigateToInputEmail(navOptions: NavOptions? = null) {
    this.navigate(inputEmailRoute, navOptions)
}