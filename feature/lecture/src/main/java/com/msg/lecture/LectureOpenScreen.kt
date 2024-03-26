package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.InputMainContentTextField
import com.msg.design_system.component.textfield.InputTitleTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.viewmodel.LectureViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayAt

@Composable
fun LectureOpenRoute(
    onActionClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel()
) {
    val currentDate = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val currentDateTime: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val currentTime: LocalTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).let {
            LocalTime(it.hour, it.minute, it.second)
        }


    LectureOpenScreen(
        onActionClicked = {
            onActionClicked()
            viewModel.openLecture(
                name = viewModel.name.value,
                content = viewModel.content.value,
                semester = viewModel.semester.value,
                division = viewModel.division.value,
                department = viewModel.department.value,
                line = viewModel.line.value,
                userId = viewModel.userId.value,
                startDate = viewModel.startDate.value ?: currentDateTime,
                endDate = viewModel.endDate.value ?: currentDateTime,
                completeDate = viewModel.completeDate.value ?: currentDate,
                startTime = viewModel.startTime.value ?: currentTime,
                endTime = viewModel.endTime.value ?: currentTime,
                lectureType = viewModel.lectureType.value,
                credit = viewModel.credit.value,
                maxRegisteredUser = viewModel.maxRegisteredUser.value,
            )
        },
        onBackClicked = onBackClicked,
        onSettingClicked = { name, content ->
            viewModel.name.value = name
            viewModel.content.value = content
            onSettingClicked()
        },
        savedName = viewModel.name.value,
        savedContent = viewModel.content.value
    )
}

@Composable
fun LectureOpenScreen(
    onActionClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSettingClicked: (name: String, content: String) -> Unit,
    savedName: String,
    savedContent: String,
) {
    val name = remember { mutableStateOf(savedName) }
    val content = remember { mutableStateOf(savedContent) }

    val isDialogVisible = remember { mutableStateOf(false) }

    val maxTitleLength = 100
    val maxContentLength = 1000

    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = Modifier
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClick = { onBackClicked() }
                )

                Spacer(modifier = Modifier.height(17.dp))

                Column(
                    modifier = Modifier
                        .background(color = colors.WHITE)
                        .wrapContentSize()
                        .padding(horizontal = 24.dp),

                    ) {
                    InputTitleTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        placeholder = "강의 제목 (100자 이내)",
                        onClick = {},
                        onClickButton = {},
                        onValueChange = {},
                        maxTextLength = maxTitleLength
                    )


                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(color = colors.G9)
                    )

                    InputMainContentTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        placeholder = "본문 입력 (1000자 이내)",
                        onClick = {},
                        onClickButton = {},
                        onValueChange = {},
                        maxTextLength = maxContentLength
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 176.dp)
                    .padding(horizontal = 24.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = colors.G9)
            )

            Spacer(modifier = Modifier.height(24.dp))
            DetailSettingButton(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 24.dp),
                type = "강의"
            ) {
                onSettingClicked(name.value, content.value)
            }

            BitgoeulButton(
                text = "강의 개설 신청",
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 24.dp),
                state = if (name.value.isNotEmpty() && content.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
            ) {
                isDialogVisible.value = true
            }
        }

        PositiveActionDialog(
            title = "강의 개설하시겠습니까?",
            positiveAction = "개설",
            content = name.value,
            isVisible = isDialogVisible.value,
            onQuit = { isDialogVisible.value = false },
            onActionClicked = onActionClicked
        )
    }
}


@Preview
@Composable
fun LectureOpenScreenPre() {
    LectureOpenScreen(
        onActionClicked = {},
        onSettingClicked = { _, _ -> },
        savedName = "",
        savedContent = "",
        onBackClicked = {}
    )
}