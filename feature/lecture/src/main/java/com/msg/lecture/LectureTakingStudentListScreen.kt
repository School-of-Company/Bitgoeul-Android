package com.msg.lecture

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureTakingStudentList
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.response.lecture.GetTakingLectureStudentListResponse
import java.util.UUID

@Composable
internal fun LectureTakingStudentListRoute(
    onBackClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
) {
    LaunchedEffect(true) {
        viewModel.getTakingLectureStudentList()
        getTakingLectureStudentList(
            viewModel = viewModel,
            onSuccess = { response ->
                viewModel.takingLectureStudentList.value = response
            }
        )
    }
    LectureTakingStudentListScreen(
        data = viewModel.takingLectureStudentList.value,
        onChangeCompleteState = { isComplete, studentId ->
            viewModel.editLectureCourseCompletionStatus(studentId, isComplete)
        },
        onBackClicked = onBackClicked
    )
}

private suspend fun getTakingLectureStudentList(
    viewModel: LectureViewModel,
    onSuccess: (data: GetTakingLectureStudentListResponse) -> Unit,
) {
    viewModel.getTakingLectureStudentListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
internal fun LectureTakingStudentListScreen(
    data: GetTakingLectureStudentListResponse? = null,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onChangeCompleteState: (isComplete: Boolean, studentId: UUID) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .padding(horizontal = 28.dp)
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(26.dp))

            GoBackTopBar(
                icon = { GoBackIcon() },
                text = "돌아가기",
                onClick = { onBackClicked() }
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "강의 신청자 명단",
                style = typography.titleMedium,
                color = colors.BLACK
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "강의 이수 여부",
                style = typography.labelMedium,
                color = colors.G2
            )

            if (data != null) {
                LectureTakingStudentList(
                    data = data.students,
                    onChangeCompleteState = { isComplete, studentId ->
                        onChangeCompleteState(isComplete, studentId)
                    }
                )
            }
        }
    }
}