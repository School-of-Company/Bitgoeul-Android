package com.msg.bitgoeul_android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.my_page.navigation.myPageRoute
import com.msg.bitgoeul_android.navigation.BitgoeulNavHost
import com.msg.bitgoeul_android.navigation.TopLevelDestination
import com.msg.club.navigation.clubRoute
import com.msg.design_system.component.navigation.BitgoeulNavigationBar
import com.msg.design_system.component.navigation.BitgoeulNavigationBarItem
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.theme.Typography
import com.msg.design_system.theme.color.BitgoeulColor
import com.msg.lecture.navigation.lectureListRoute
import com.msg.main.navigation.mainPageRoute
import com.msg.post.navigation.postRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BitgoeulApp(
    windowSizeClass: WindowSizeClass,
    appState: BitgoeulAppState = rememberBitgoeulAppState(
        windowSizeClass = windowSizeClass
    )
) {
    val isBottomBarVisible = remember { mutableStateOf(true) }
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val topLevelDestinationRoute = arrayOf(
        lectureListRoute,
        clubRoute,
        mainPageRoute,
        postRoute,
        myPageRoute
    )

    navBackStackEntry?.destination?.route?.let {
        isBottomBarVisible.value = topLevelDestinationRoute.contains(it)
    }

    BitgoeulAndroidTheme { colors, _ ->
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = colors.WHITE,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                if (isBottomBarVisible.value) {
                    BitgoeulBottomBar(
                        destination = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        ) { _ ->
            BitgoeulNavHost(appState = appState)
        }
    }
}

@Composable
fun BitgoeulBottomBar(
    destination: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    BitgoeulNavigationBar {
        destination.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            BitgoeulNavigationBarItem(
                selected = selected,
                onClicked = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null,
                        tint = BitgoeulColor.P5
                    )
                },
                label = {
                    Text(
                        text = destination.iconText,
                        style = Typography.labelMedium
                    )
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false