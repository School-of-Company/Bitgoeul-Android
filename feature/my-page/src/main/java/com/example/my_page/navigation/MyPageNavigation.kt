package com.example.my_page.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.my_page.MyPageRoute
import com.example.my_page.PasswordChangeRoute

const val myPageRoute = "my_page_route"
const val passwordChangeRoute = "password_change_route"

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    this.navigate(myPageRoute, navOptions)
}

fun NavGraphBuilder.myPageScreen(onPasswordChangeClicked: () -> Unit, onWithdrawClicked: () -> Unit) {
    composable(route = myPageRoute) {
        MyPageRoute(
            onPasswordChangeClicked = onPasswordChangeClicked,
            onWithdrawClicked = onWithdrawClicked
        )
    }
}

fun NavController.navigateToPasswordChange(navOptions: NavOptions? = null) {
    this.navigate(passwordChangeRoute, navOptions)
}

fun NavGraphBuilder.changePasswordScreen(onSuccessScreenButtonClicked: () -> Unit, onBackClicked: () -> Unit) {
    composable(route = passwordChangeRoute) {
        PasswordChangeRoute(
            onSuccessScreenButtonClicked = onSuccessScreenButtonClicked,
            onBackClicked = onBackClicked
        )
    }
}