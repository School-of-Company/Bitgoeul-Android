package com.msg.bitgoeul_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bitgoeul.email.navigation.emailSendInformScreen
import com.bitgoeul.email.navigation.inputEmailScreen
import com.bitgoeul.email.navigation.inputNewPasswordScreen
import com.bitgoeul.email.navigation.navigateToEmailSendInform
import com.bitgoeul.email.navigation.navigateToInputEmail
import com.bitgoeul.email.navigation.navigateToInputNewPassword
import com.bitgoeul.email.navigation.navigateToPasswordChangeSuccess
import com.bitgoeul.email.navigation.passwordChangeSuccessScreen
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.navigation.loginScreen
import com.bitgoeul.login.navigation.navigateToLogin
import com.example.my_page.navigation.changePasswordScreen
import com.example.my_page.navigation.myPageScreen
import com.example.my_page.navigation.navigateToMyPage
import com.example.my_page.navigation.navigateToPasswordChange
import com.msg.bitgoeul_android.ui.BitgoeulAppState
import com.msg.bitgoeul_android.ui.navigateWithPopUpToLogin
import com.msg.certification.navigation.addCertificationScreen
import com.msg.certification.navigation.certificationScreen
import com.msg.certification.navigation.navigateToAddCertificationPage
import com.msg.club.navigation.clubDetailScreen
import com.msg.club.navigation.clubScreen
import com.msg.club.navigation.navigateToClubDetailPage
import com.msg.lecture.navigation.lectureDetailScreen
import com.msg.lecture.navigation.lectureListScreen
import com.msg.lecture.navigation.lectureTakingStudentListScreen
import com.msg.lecture.navigation.navigateToLecture
import com.msg.lecture.navigation.navigateToLectureDetail
import com.msg.lecture.navigation.navigateToLectureTakingStudentList
import com.msg.post.navigation.navigateToPostDetailPage
import com.msg.post.navigation.postDetailScreen
import com.msg.post.navigation.postScreen
import com.msg.sign_up.navigation.navigateToSignUp
import com.msg.sign_up.navigation.navigateToSignUpFinish
import com.msg.sign_up.navigation.signUpFinishScreen
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
    startDestination: String = loginRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginScreen(
            onSignUpClicked = navController::navigateToSignUp,
            onFindPasswordClicked = navController::navigateToInputEmail,
            onLoginClicked = navController::navigateToLecture
        )
        inputEmailScreen(
            onBackClicked = { navController.navigateWithPopUpToLogin(loginRoute) },
            onNextClicked = navController::navigateToEmailSendInform
        )
        emailSendInformScreen(
            onBackClicked = navController::navigateToInputEmail,
            onMoveNewPasswordClicked = navController::navigateToInputNewPassword
        )
        inputNewPasswordScreen(
            onBackClicked = navController::navigateToInputEmail,
            onNextClicked = navController::navigateToPasswordChangeSuccess
        )
        passwordChangeSuccessScreen(
            onBackClicked = navController::navigateToLogin
        )
        signUpScreen(
            onBackClicked = navController::navigateUp,
            onEnterFinished = navController::navigateToSignUpFinish
        )
        signUpFinishScreen(
            onBackClicked = navController::navigateToLogin
        )
        studentActivityScreen(
            onAddClicked = navController::navigateToAddActivity,
            onItemClicked = navController::navigateToStudentDetailActivity,
            onBackClicked = navController::navigateUp
        )
        studentDetailActivityScreen(
            onActionEnd = navController::navigateUp,
            onEditClicked = navController::navigateToAddActivity,
            onBackClicked = navController::navigateUp
        )
        studentAddActivityScreen(
            onActionClicked = navController::navigateUp,
            onSettingClicked = navController::navigateToDetailSettingActivity,
            onBackClicked = navController::navigateUp
        )
        studentDetailSettingActivityScreen(
            onCloseClicked = navController::navigateUp,
            onApplyClicked = navController::navigateUp
        )
        lectureListScreen(
            onItemClicked = navController::navigateToLectureDetail,
        )
        lectureDetailScreen(
            onBackClicked = navController::navigateUp,
            onLectureTakingStudentListScreenClicked = navController::navigateToLectureTakingStudentList
        )
        lectureTakingStudentListScreen(
            onBackClicked = navController::navigateUp,
        )
        myPageScreen(
            onPasswordChangeClicked = navController::navigateToPasswordChange,
            onWithdrawClicked = navController::navigateToLogin,
            onLogoutClicked = navController::navigateToLogin
        )
        changePasswordScreen(
            onSuccessScreenButtonClicked = navController::navigateToMyPage,
            onBackClicked = navController::navigateUp
        )
        postScreen(
            onItemClick = navController::navigateToPostDetailPage,
        )
        postDetailScreen(
            // onEditClicked = navController::navigateToPostAddPage,
            onDeleteClicked = navController::navigateUp,
            onBackClicked = navController::navigateUp
        )
        clubScreen(
            onItemClicked = navController::navigateToClubDetailPage
        )
        clubDetailScreen(
            onBackClickedByAdmin = navController::navigateUp,
            onBackClicked = navController::navigateUp
        )
        certificationScreen(
            onHumanClicked = navController::navigateToMyPage,
            onEditClicked = navController::navigateToAddCertificationPage
        )
        addCertificationScreen(
            onBackClicked = navController::navigateUp
        )
    }
}