package com.msg.lecture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.lecture.LectureDetailRoute
import com.msg.lecture.LectureListRoute
import com.msg.lecture.LectureTakingStudentListRoute

const val lectureListRoute = "lecture_list_route"

const val lectureDetailRoute = "lecture_detail_route"

const val lectureTakingStudentListRoute = "lecture_taking_student_list_route"
fun NavController.navigateToLecture(navOptions: NavOptions? = null) {
    this.navigate(lectureListRoute, navOptions)
}

fun NavController.navigateToLectureDetail(navOptions: NavOptions? = null) {
    this.navigate(lectureDetailRoute, navOptions)
}

fun NavController.navigateToLectureTakingStudentList(navOptions: NavOptions? = null) {
    this.navigate(lectureTakingStudentListRoute, navOptions)
}

fun NavGraphBuilder.lectureListScreen(
    onItemClicked: () -> Unit,
) {
    composable(route = lectureListRoute) {
        LectureListRoute(
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

fun NavGraphBuilder.lectureTakingStudentListScreen(
    onBackClicked: () -> Unit,
) {
    composable(route = lectureTakingStudentListRoute) {
        LectureTakingStudentListRoute(
            onBackClicked = onBackClicked,
        )
    }
}