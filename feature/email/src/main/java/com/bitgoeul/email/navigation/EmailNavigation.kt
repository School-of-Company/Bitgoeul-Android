package com.bitgoeul.email.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bitgoeul.email.EmailSendInformRoute
import com.bitgoeul.email.InputEmailRoute

const val inputEmailRoute = "input_email_route"

const val emailSendInformRoute = "email_send_inform_route"

fun NavController.navigateToInputEmail(navOptions: NavOptions? = null) {
    this.navigate(inputEmailRoute, navOptions)
}

fun NavController.navigateToEmailSendInform(navOptions: NavOptions? = null) {
    this.navigate(emailSendInformRoute, navOptions)
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

fun NavGraphBuilder.emailSendInformScreen(
    onBackClicked: () -> Unit,
    onMoveNewPasswordClick: () -> Unit,
) {
    composable(route = emailSendInformRoute) {
        EmailSendInformRoute(
            onBackClicked = onBackClicked,
            onMoveNewPasswordClick = onMoveNewPasswordClick,
        )
    }
}