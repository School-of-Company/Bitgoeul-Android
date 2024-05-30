package com.msg.lecture

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.viewmodel.LectureViewModel
import java.time.LocalDateTime

@Composable
fun LectureOpenRoute(
    onActionClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
) {
    LectureOpenScreen(
        onActionClicked = {
            viewModel.openLecture(
                name = viewModel.name.value,
                content = viewModel.content.value,
                semester = viewModel.semester.value,
                division = viewModel.division.value,
                department = viewModel.department.value,
                line = viewModel.line.value,
                userId = viewModel.userId.value,
                startDate = viewModel.startDate.value ?: LocalDateTime.now(),
                endDate = viewModel.endDate.value ?: LocalDateTime.now(),
                lectureType = viewModel.lectureType.value,
                credit = viewModel.credit.value,
                maxRegisteredUser = viewModel.maxRegisteredUser.value,
                essentialComplete = viewModel.essentialComplete.value
            )
            onActionClicked()
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
    modifier: Modifier = Modifier,
) {
    val name = remember { mutableStateOf(savedName) }
    val content = remember { mutableStateOf(savedContent) }

    val isDialogVisible = remember { mutableStateOf(false) }

    val maxTitleLength = 100
    val maxContentLength = 1000

    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
            ) {
                Spacer(modifier = modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }

                Spacer(modifier = modifier.height(16.dp))

                Column(
                    modifier = modifier
                        .verticalScroll(scrollState)
                        .fillMaxHeight()
                ) {
                    BasicTextField(
                        modifier = modifier
                            .fillMaxWidth(),
                        value = name.value,
                        onValueChange = { if (it.length <= maxTitleLength) name.value = it },
                        textStyle = typography.titleSmall,
                        decorationBox = { innerTextField ->
                            if (name.value.isEmpty()) Text(
                                text = "강의 제목 (100자 이내)",
                                style = typography.titleSmall,
                                color = colors.G1
                            )
                            innerTextField()
                        }
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = content.value,
                        onValueChange = { if (it.length <= maxContentLength) content.value = it },
                        textStyle = typography.bodySmall,
                        decorationBox = { innerTextField ->
                            if (content.value.isEmpty()) Text(
                                text = "본문 입력 (1000자 이내)",
                                style = typography.bodySmall,
                                color = colors.G1
                            )
                            innerTextField()
                        }
                    )
                }
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                HorizontalDivider(
                    modifier = modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = colors.G9
                )

                Spacer(modifier = modifier.height(24.dp))

                DetailSettingButton(
                    modifier = modifier
                        .fillMaxWidth(),
                    type = "강의"
                ) {
                    onSettingClicked(name.value, content.value)
                }

                Spacer(modifier = modifier.height(8.dp))

                BitgoeulButton(
                    text = "강의 개설 신청",
                    modifier = modifier
                        .fillMaxWidth(),
                    state = if (name.value.isNotEmpty() && content.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
                ) {
                    isDialogVisible.value = true
                }
                Spacer(modifier = modifier.height(16.dp))
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
}
