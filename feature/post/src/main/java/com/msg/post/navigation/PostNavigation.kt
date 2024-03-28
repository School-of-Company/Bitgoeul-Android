package com.msg.post.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.post.PostScreenRoute
import java.util.UUID

const val postRoute = "post_route"
const val postDetailRoute = "post_detail_route"
const val postAddRoute = "post_Add_route"
const val postDetailSettingRoute = "post_detail_setting_route"

fun NavController.navigateToPostPage(navOptions: NavOptions? = null) {
    this.navigate(postRoute, navOptions)
}

fun NavGraphBuilder.postScreen(onItemClick: () -> Unit, onAddClicked: () -> Unit) {
    composable(route = postRoute) {
        PostScreenRoute(
            onItemClicked = onItemClick,
            onAddClicked = onAddClicked
        )
    }
}

fun NavController.navigateToPostDetailPage(navOptions: NavOptions? = null) {
    this.navigate(postDetailRoute, navOptions)
}

fun NavController.navigateToPostAddPage(navOptions: NavOptions? = null) {
    this.navigate(postAddRoute, navOptions)
}

fun NavController.navigateToPostDetailSettingPage(navOptions: NavOptions? = null) {
    this.navigate(postDetailSettingRoute, navOptions)
}