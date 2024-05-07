package com.msg.lecture

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.icon.CircledAddIcon
import com.msg.design_system.component.icon.CircledGroupIcon
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureTakingStudentList
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.response.lecture.GetTakingLectureStudentListResponse
import java.util.UUID

@Composable
fun LectureTakingStudentListRoute(
    onBackClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
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

suspend fun getTakingLectureStudentList(
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
fun LectureTakingStudentListScreen(
    data: GetTakingLectureStudentListResponse? = null,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onChangeCompleteState: (isComplete: Boolean, studentId: UUID ) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(20.dp))

            GoBackTopBar(
                icon = { GoBackIcon() },
                text = "돌아가기",
                onClick = { onBackClicked() }
            )

            Spacer(modifier = modifier.height(8.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "강의 신청자 명단",
                    style = typography.titleMedium,
                    color = colors.BLACK
                )

                Spacer(modifier = modifier.weight(1f))

                CircledGroupIcon(
                    modifier = modifier
                        .clickable {

                        }
                )

                Spacer(modifier = modifier.width(24.dp))

                CircledAddIcon(
                    modifier = modifier
                        .clickable {

                        }
                )
            }

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "강의 이수 여부",
                style = typography.labelMedium,
                color = colors.G2
            )

            if (data != null) {
                LectureTakingStudentList(
                    modifier = modifier,
                    data = data.students,
                    onChangeCompleteState = { isComplete, studentId ->
                        onChangeCompleteState(isComplete, studentId)
                    }
                )
            }
        }
    }
}