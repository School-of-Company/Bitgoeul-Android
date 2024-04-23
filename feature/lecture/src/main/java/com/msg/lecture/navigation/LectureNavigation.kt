package com.msg.lecture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.lecture.LectureDetailRoute
import com.msg.lecture.LectureDetailSettingRoute
import com.msg.lecture.LectureListRoute
import com.msg.lecture.LectureOpenRoute
import com.msg.model.remote.enumdatatype.LectureType

const val lectureListRoute = "lecture_list_route"

const val lectureDetailRoute = "lecture_detail_route"

const val lectureDetailSettingRoute = "lecture_detail_setting_route"

const val lectureOpenRoute = "lecture_open_route"
fun NavController.navigateToLecture(navOptions: NavOptions? = null) {
    this.navigate(lectureListRoute, navOptions)
}

fun NavController.navigateToLectureDetail(navOptions: NavOptions? = null) {
    this.navigate(lectureDetailRoute, navOptions)
}

fun NavController.navigateToLectureOpen(navOptions: NavOptions? = null) {
    this.navigate(lectureOpenRoute, navOptions)
}

fun NavController.navigateToLectureDetailSetting(navOptions: NavOptions? = null) {
    this.navigate(lectureDetailSettingRoute, navOptions)
}

fun NavGraphBuilder.lectureListScreen(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    composable(route = lectureListRoute) {
        LectureListRoute(
            onOpenClicked = onOpenClicked,
            onItemClicked = onItemClicked,
            onBackClicked = onBackClicked,
        )
    }
}

fun NavGraphBuilder.lectureDetailScreen(
    onBackClicked: () -> Unit,
) {
    composable(route = lectureDetailRoute) {
        LectureDetailRoute(
          onBackClicked = onBackClicked,
        )
    }
}

fun NavGraphBuilder.lectureOpenScreen(
    onActionClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    composable(route = lectureOpenRoute) {
        LectureOpenRoute(
            onActionClicked = onActionClicked,
            onSettingClicked = onSettingClicked,
            onBackClicked = onBackClicked,
        )
    }
}

fun NavGraphBuilder.lectureDetailSettingScreen(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
) {
    composable(route = lectureDetailSettingRoute) {
        LectureDetailSettingRoute(
            onCloseClicked = onCloseClicked,
            onApplyClicked = onApplyClicked,
        )
    }
}
