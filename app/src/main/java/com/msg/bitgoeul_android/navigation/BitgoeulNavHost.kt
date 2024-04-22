package com.msg.bitgoeul_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.navigation.loginScreen
import com.bitgoeul.login.navigation.navigateToLogin
import com.example.my_page.navigation.changePasswordScreen
import com.example.my_page.navigation.myPageScreen
import com.example.my_page.navigation.navigateToMyPage
import com.example.my_page.navigation.navigateToPasswordChange
import com.msg.sign_up.navigation.navigateToSignUp
import com.msg.bitgoeul_android.ui.BitgoeulAppState
import com.msg.club.navigation.clubDetailScreen
import com.msg.club.navigation.clubScreen
import com.msg.club.navigation.navigateToClubDetailPage
import com.msg.post.navigation.navigateToPostAddPage
import com.msg.post.navigation.navigateToPostDetailPage
import com.msg.post.navigation.navigateToPostDetailSettingPage
import com.msg.post.navigation.postAddScreen
import com.msg.post.navigation.postDetailScreen
import com.msg.post.navigation.postDetailSettingScreen
import com.msg.post.navigation.postScreen
import com.msg.sign_up.navigation.signUpScreen
import com.msg.student_activity.navigation.navigateToAddActivity
import com.msg.student_activity.navigation.navigateToDetailSettingActivity
import com.msg.student_activity.navigation.navigateToStudentActivity
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
        myPageScreen(
            onPasswordChangeClicked = navController::navigateToPasswordChange,
            onWithdrawClicked = navController::navigateToLogin
        )
        changePasswordScreen(
            onSuccessScreenButtonClicked = navController::navigateToMyPage,
            onBackClicked = navController::popBackStack
        )
        postScreen(
            onItemClick = navController::navigateToPostDetailPage,
            onAddClicked = navController::navigateToPostAddPage
        )
        postDetailScreen(
            onEditClicked = navController::navigateToPostAddPage,
            onDeleteClicked = navController::popBackStack,
            onBackClicked = navController::popBackStack
        )
        postAddScreen(
            onSettingClicked = navController::navigateToPostDetailSettingPage,
            onBackClicked = navController::popBackStack,
            onAddClicked = navController::popBackStack
        )
        postDetailSettingScreen(
            onCloseClicked = navController::popBackStack
        )
        clubScreen(
            onItemClicked = navController::navigateToClubDetailPage
        )
        clubDetailScreen(
            onBackClickedByAdmin = navController::popBackStack,
            //onBackClicked = navController::navigateToMainPage
            onBackClicked = navController::popBackStack
        )
    }
}