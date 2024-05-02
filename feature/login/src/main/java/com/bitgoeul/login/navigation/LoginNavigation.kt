package com.bitgoeul.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bitgoeul.login.LoginRoute

const val loginRoute = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onSignUpClick: () -> Unit,
    onFindPasswordClick: () -> Unit,
) {
    composable(route = loginRoute) {
        LoginRoute(
            onSignUpClick = onSignUpClick,
            onFindPasswordClick = onFindPasswordClick
        )
    }
}