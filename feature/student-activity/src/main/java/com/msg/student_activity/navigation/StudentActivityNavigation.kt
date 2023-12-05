package com.msg.student_activity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.msg.student_activity.ActivityDetailRoute
import com.msg.student_activity.ActivityDetailSettingRoute
import com.msg.student_activity.AddActivityRoute
import com.msg.student_activity.AddActivityScreen
import com.msg.student_activity.StudentActivityRoute
import java.util.UUID

const val studentActivityRoute = "student_activity_route"
const val studentActivityDetailRoute = "student_activity_detail_route"
const val studentActivityAddRoute = "student_activity_add_route"
const val studentActivityDetailSettingRoute = "student_activity_detail_setting_route"

fun NavController.navigateToStudentActivity(navOptions: NavOptions? = null) {
    this.navigate(studentActivityRoute, navOptions)
}

fun NavGraphBuilder.studentActivityScreen(onAddClick: () -> Unit, onItemClick: () -> Unit, id: UUID? = null) {
    composable(route = studentActivityRoute) {
        StudentActivityRoute(
            onAddClicked = onAddClick,
            onItemClicked = onItemClick,
            id = id
        )
    }
}

fun NavController.navigateToStudentDetailActivity(navOptions: NavOptions? = null) {
    this.navigate(studentActivityDetailRoute, navOptions)
}

fun NavGraphBuilder.studentDetailActivityScreen(onActionEnd: () -> Unit, onEditClicked: () -> Unit) {
    composable(route = studentActivityDetailRoute) {
        ActivityDetailRoute(
            onActionEnd = onActionEnd,
            onEditClicked = onEditClicked
        )
    }
}

fun NavController.navigateToAddActivity(navOptions: NavOptions? = null) {
    this.navigate(studentActivityAddRoute, navOptions)
}

fun NavGraphBuilder.studentAddActivityScreen(onActionClicked: () -> Unit, onSettingClicked: () -> Unit) {
    composable(route = studentActivityAddRoute) {
        AddActivityRoute(
            onActionClicked = onActionClicked,
            onSettingClicked = onSettingClicked
        )
    }
}

fun NavController.navigateToDetailSettingActivity(navOptions: NavOptions? = null) {
    this.navigate(studentActivityDetailSettingRoute, navOptions)
}

fun NavGraphBuilder.studentDetailSettingActivityScreen(onCloseClick: () -> Unit, onApplyClicked: () -> Unit) {
    composable(route = studentActivityDetailSettingRoute) {
        ActivityDetailSettingRoute(
            onCloseClick = onCloseClick,
            onApplyClicked = onApplyClicked
        )
    }
}