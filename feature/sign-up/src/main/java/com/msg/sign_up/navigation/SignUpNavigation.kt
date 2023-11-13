package com.msg.sign_up.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.sign_up.SignUpRoute

const val signUpRoute = "sign_up_route"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signUpScreen(
    onBackClick: () -> Unit
) {
    composable(route = signUpRoute) {
        SignUpRoute(
            onBackClick = onBackClick
        )
    }
}