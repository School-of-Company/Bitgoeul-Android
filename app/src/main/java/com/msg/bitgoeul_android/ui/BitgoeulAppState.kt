package com.msg.bitgoeul_android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.my_page.navigation.myPageRoute
import com.example.my_page.navigation.navigateToMyPage
import com.msg.bitgoeul_android.navigation.TopLevelDestination
import com.msg.club.navigation.clubRoute
import com.msg.club.navigation.navigateToClubPage
import com.msg.lecture.navigation.lectureListRoute
import com.msg.lecture.navigation.navigateToLecture
import com.msg.post.navigation.navigateToPostPage
import com.msg.post.navigation.postRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBitgoeulAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): BitgoeulAppState {
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
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLeverDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            lectureListRoute -> TopLevelDestination.LECTURE
            clubRoute -> TopLevelDestination.CLUB
            postRoute -> TopLevelDestination.POST
            myPageRoute -> TopLevelDestination.MY_PAGE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            when (topLevelDestination) {
                TopLevelDestination.LECTURE -> navController.navigateToLecture(topLevelNavOptions)
                TopLevelDestination.CLUB -> navController.navigateToClubPage(topLevelNavOptions)
                TopLevelDestination.POST -> navController.navigateToPostPage(topLevelNavOptions)
                TopLevelDestination.MY_PAGE -> navController.navigateToMyPage(topLevelNavOptions)
            }
        }
    }
}

fun NavController.navigateWithPopUpToLogin(loginRoute: String) {
    this.navigate(loginRoute) {
        popUpTo(loginRoute) { inclusive = true }
    }
}