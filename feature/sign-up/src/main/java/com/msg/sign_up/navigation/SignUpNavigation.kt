package com.msg.sign_up.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.sign_up.SignUpRoute
import com.msg.sign_up.SignupFinishScreenRoute

const val signUpRoute = "sign_up_route"
const val signUpFinishRoute = "sign_up_finish_route"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signUpScreen(
    onBackClick: () -> Unit,
    onEnterFinished: () -> Unit
) {
    composable(route = signUpRoute) {
        SignUpRoute(
            onBackClick = onBackClick,
            onEnterFinished = onEnterFinished
        )
    }
}

fun NavController.navigateToSignUpFinish(navOptions: NavOptions? = null) {
    this.navigate(signUpFinishRoute, navOptions)
}

fun NavGraphBuilder.signUpFinishScreen(
    onBackClicked: () -> Unit
) {
    composable(route = signUpFinishRoute) {
        SignupFinishScreenRoute(
            onBackClicked = onBackClicked
        )
    }
}