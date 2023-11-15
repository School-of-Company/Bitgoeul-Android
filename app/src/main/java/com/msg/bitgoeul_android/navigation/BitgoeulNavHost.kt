package com.msg.bitgoeul_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.navigation.loginScreen
import com.msg.sign_up.navigation.navigateToSignUp
import com.msg.bitgoeul_android.ui.BitgoeulAppState
import com.msg.sign_up.navigation.signUpScreen

@Composable
fun BitgoeulNavHost(
    appState: BitgoeulAppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginScreen(
            onSignUpClick = navController::navigateToSignUp
        )
        signUpScreen(
            onBackClick = navController::popBackStack
        )
    }
}