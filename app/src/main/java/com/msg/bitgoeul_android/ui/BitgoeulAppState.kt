package com.msg.bitgoeul_android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bitgoeul.login.navigation.loginRoute
import com.msg.bitgoeul_android.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBitgoeulAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
):BitgoeulAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass
    ) {
        BitgoeulAppState(
            navController,
            coroutineScope,
            windowSizeClass
        )
    }
}

@Stable
class BitgoeulAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLeverDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            loginRoute -> TopLevelDestination.LOGIN
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
}