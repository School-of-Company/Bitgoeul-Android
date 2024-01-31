package com.msg.bitgoeul_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.navigation.loginScreen
import com.msg.sign_up.navigation.navigateToSignUp
import com.msg.bitgoeul_android.ui.BitgoeulAppState
import com.msg.lecture.navigation.lectureListScreen
import com.msg.sign_up.navigation.signUpScreen
import com.msg.student_activity.navigation.navigateToAddActivity
import com.msg.student_activity.navigation.navigateToDetailSettingActivity
import com.msg.student_activity.navigation.navigateToStudentDetailActivity
import com.msg.student_activity.navigation.studentActivityScreen
import com.msg.student_activity.navigation.studentAddActivityScreen
import com.msg.student_activity.navigation.studentDetailActivityScreen
import com.msg.student_activity.navigation.studentDetailSettingActivityScreen

@Composable
fun BitgoeulNavHost(
    appState: BitgoeulAppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginScreen(
            onSignUpClick = navController::navigateToSignUp
        )
        signUpScreen(
            onBackClick = navController::popBackStack
        )
        studentActivityScreen(
            onAddClick = navController::navigateToAddActivity,
            onItemClick = navController::navigateToStudentDetailActivity,
            onBackClicked = navController::popBackStack
        )
        studentDetailActivityScreen(
            onActionEnd = navController::popBackStack,
            onEditClicked = navController::navigateToAddActivity,
            onBackClicked = navController::popBackStack
        )
        studentAddActivityScreen(
            onActionClicked = navController::popBackStack,
            onSettingClicked = navController::navigateToDetailSettingActivity,
            onBackClicked = navController::popBackStack
        )
        studentDetailSettingActivityScreen(
            onCloseClick = navController::popBackStack,
            onApplyClicked = navController::popBackStack
        )
        lectureListScreen(
            onOpenClicked = navController::popBackStack,
            onItemClicked = navController::popBackStack,
            onBackClicked = navController::popBackStack,
        )
    }
}