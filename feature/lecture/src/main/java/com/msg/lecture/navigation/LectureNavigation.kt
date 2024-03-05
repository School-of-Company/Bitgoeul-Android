package com.msg.lecture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.lecture.LectureListRoute
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.LectureType

const val lectureListRoute = "lecture_list_route"

fun NavController.navigateToLecture(navOptions: NavOptions? = null) {
    this.navigate(lectureListRoute, navOptions)
}

fun NavGraphBuilder.lectureListScreen(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onBackClicked: () -> Unit,
    type: LectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
) {
    composable(route = lectureListRoute) {
        LectureListRoute(
            onOpenClicked = onOpenClicked,
            onItemClicked = onItemClicked,
            onBackClicked = onBackClicked,
            type = type,
        )
    }
}