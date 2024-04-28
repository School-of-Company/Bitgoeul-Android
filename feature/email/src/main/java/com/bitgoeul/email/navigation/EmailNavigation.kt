package com.bitgoeul.email.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bitgoeul.email.InputEmailRoute

const val inputEmailRoute = "input_email_route"

fun NavController.navigateToInputEmail(navOptions: NavOptions? = null) {
    this.navigate(inputEmailRoute, navOptions)
}

fun NavGraphBuilder.inputEmailScreen(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    composable(route = inputEmailRoute) {
        InputEmailRoute(
            onBackClicked = onBackClicked,
            onNextClicked = onNextClicked,
        )
    }
}