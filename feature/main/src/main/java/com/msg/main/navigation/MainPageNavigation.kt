package com.msg.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.main.MainPageScreenRoute

const val mainPageRoute = "main_page_route"

fun NavController.navigateToMainPage(navOptions: NavOptions? = null) {
    this.navigate(mainPageRoute, navOptions)
}

fun NavGraphBuilder.mainPageScreen(onLoginClicked: () -> Unit) {
    composable(route = mainPageRoute) {
        MainPageScreenRoute(
            onLoginClicked = onLoginClicked
        )
    }
}