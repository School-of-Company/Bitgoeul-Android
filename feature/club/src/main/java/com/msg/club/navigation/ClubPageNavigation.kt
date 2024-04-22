package com.msg.club.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.club.ClubDetailScreenRoute
import com.msg.club.ClubScreenRoute

const val clubRoute = "club_route"
const val clubDetailRoute = "club_detail_route"

fun NavController.navigateToClubPage(navOptions: NavOptions? = null) {
    this.navigate(clubRoute, navOptions)
}

fun NavGraphBuilder.clubScreen(onItemClicked: () -> Unit) {
    composable(route = clubRoute) {
        ClubScreenRoute(
            onItemClicked = onItemClicked
        )
    }
}

fun NavController.navigateToClubDetailPage(navOptions: NavOptions? = null) {
    this.navigate(clubDetailRoute, navOptions)
}

fun NavGraphBuilder.clubDetailScreen(onBackClicked: () -> Unit, onBackClickedByAdmin: () -> Unit) {
    composable(route = clubDetailRoute) {
        ClubDetailScreenRoute(
            onBackClicked = onBackClicked,
            onBackClickedByAdmin = onBackClickedByAdmin
        )
    }
}