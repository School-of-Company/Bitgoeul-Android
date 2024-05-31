package com.msg.lecture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.lecture.LectureDetailRoute
import com.msg.lecture.LectureDetailSettingRoute
import com.msg.lecture.LectureListRoute
import com.msg.lecture.LectureOpenRoute
import com.msg.lecture.LectureTakingStudentListRoute

const val lectureListRoute = "lecture_list_route"

const val lectureDetailRoute = "lecture_detail_route"

const val lectureDetailSettingRoute = "lecture_detail_setting_route"

const val lectureOpenRoute = "lecture_open_route"

const val lectureTakingStudentListRoute = "lecture_taking_student_list_route"
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

fun NavController.navigateToLectureTakingStudentList(navOptions: NavOptions? = null) {
    this.navigate(lectureTakingStudentListRoute, navOptions)
}

fun NavGraphBuilder.lectureListScreen(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
) {
    composable(route = lectureListRoute) {
        LectureListRoute(
            onOpenClicked = onOpenClicked,
            onItemClicked = onItemClicked,
        )
    }
}

fun NavGraphBuilder.lectureDetailScreen(
    onBackClicked: () -> Unit,
    onLectureTakingStudentListScreenClicked: () -> Unit,
) {
    composable(route = lectureDetailRoute) {
        LectureDetailRoute(
            onBackClicked = onBackClicked,
            onLectureTakingStudentListScreenClicked = onLectureTakingStudentListScreenClicked,
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

fun NavGraphBuilder.lectureTakingStudentListScreen(
    onBackClicked: () -> Unit,
) {
    composable(route = lectureTakingStudentListRoute) {
        LectureTakingStudentListRoute(
            onBackClicked = onBackClicked,
        )
    }
}