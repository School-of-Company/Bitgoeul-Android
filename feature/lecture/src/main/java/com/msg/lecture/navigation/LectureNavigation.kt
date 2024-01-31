package com.msg.lecture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.lecture.LectureListRoute
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.LectureType
import java.util.UUID

const val lectureListRoute = "lecture_list_route"

fun NavController.navigateToLecture(navOptions: NavOptions? = null) {
    this.navigate(lectureListRoute, navOptions)
}

fun NavGraphBuilder.lectureListScreen(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onBackClicked: () -> Unit,
    id: UUID? = null,
    status: ApproveStatus = ApproveStatus.APPROVED,
    type: LectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
) {
    composable(route = lectureListRoute) {
        LectureListRoute(
            onOpenClicked = onOpenClicked,
            onItemClicked = onItemClicked,
            onBackClicked = onBackClicked,
            id = id,
            status = status,
            type = type
        )
    }
}