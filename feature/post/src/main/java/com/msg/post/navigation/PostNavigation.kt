package com.msg.post.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.post.PostDetailScreenRoute
import com.msg.post.PostScreenRoute

const val postRoute = "post_route"
const val postDetailRoute = "post_detail_route"

fun NavController.navigateToPostPage(navOptions: NavOptions? = null) {
    this.navigate(postRoute, navOptions)
}

fun NavGraphBuilder.postScreen(onItemClick: () -> Unit) {
    composable(route = postRoute) {
        PostScreenRoute(
            onItemClicked = onItemClick,
        )
    }
}

fun NavController.navigateToPostDetailPage(navOptions: NavOptions? = null) {
    this.navigate(postDetailRoute, navOptions)
}

fun NavGraphBuilder.postDetailScreen(
    // onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    composable(route = postDetailRoute) {
        PostDetailScreenRoute(
            // onEditClicked = onEditClicked,
            onDeleteClicked = onDeleteClicked,
            onBackClicked = onBackClicked
        )
    }
}