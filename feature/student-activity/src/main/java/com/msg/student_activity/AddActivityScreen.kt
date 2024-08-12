package com.msg.student_activity

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import java.time.LocalDate

@Composable
fun AddActivityRoute(
    onActionClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: StudentActivityViewModel = hiltViewModel()
) {

    AddActivityScreen(
        onActionClicked = {
            onActionClicked()
            viewModel.addActivityInfo(
                title = viewModel.title.value,
                content = viewModel.content.value,
                credit = viewModel.credit.intValue,
                activityDate = viewModel.activityDate.value ?: LocalDate.now()
            )
        },
        onSettingClicked = { title, content ->
            viewModel.title.value = title
            viewModel.content.value = content
            onSettingClicked()
        },
        onBackClicked = onBackClicked,
        savedTitle = viewModel.title.value,
        savedContent = viewModel.content.value,
        detailState = viewModel.detailState.value
    )
}

@Composable
fun AddActivityScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    onActionClicked: () -> Unit,
    onSettingClicked: (title: String, content: String) -> Unit,
    onBackClicked: () -> Unit,
    savedTitle: String,
    savedContent: String,
    detailState: Boolean
) {

    val title = remember { mutableStateOf(savedTitle) }
    val content = remember { mutableStateOf(savedContent) }

    val isDialogVisible = remember { mutableStateOf(false) }

    val maxTitleLength = 100
    val maxContentLength = 1000

    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Spacer(modifier = modifier.height(16.dp))
                Column(
                    modifier = modifier
                        .padding(horizontal = 28.dp)
                        .verticalScroll(scrollState)
                        .weight(1f)
                ) {
                    BasicTextField(
                        modifier = modifier
                            .fillMaxWidth(),
                        value = title.value,
                        onValueChange = { if (it.length <= maxTitleLength) title.value = it },
                        textStyle = typography.titleSmall,
                        decorationBox = { innerTextField ->
                            if (title.value.isEmpty()) Text(
                                text = "활동 제목 (100자 이내)",
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
                        onValueChange = {
                            if (it.length <= maxContentLength) content.value = it
                        },
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
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                ) {
                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    DetailSettingButton(
                        modifier = modifier.fillMaxWidth(),
                        type = "활동"
                    ) {
                        onSettingClicked(title.value, content.value)
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    BitgoeulButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "활동 추가",
                        state = if (title.value.isNotEmpty() && content.value.isNotEmpty() && detailState) ButtonState.Enable else ButtonState.Disable
                    ) {
                        isDialogVisible.value = true
                    }
                    Spacer(modifier = modifier.height(16.dp))
                }
                PositiveActionDialog(
                    title = "활동 추가하시겠습니까?",
                    positiveAction = "신청",
                    content = title.value,
                    isVisible = isDialogVisible.value,
                    onQuit = { isDialogVisible.value = false },
                    onActionClicked = onActionClicked
                )
            }
        }
    }
}

@Preview
@Composable
fun AddActivityScreenPre() {
    AddActivityScreen(
        onActionClicked = {},
        onSettingClicked = { _, _ -> },
        savedTitle = "",
        savedContent = "",
        detailState = false,
        onBackClicked = {},
        focusManager = LocalFocusManager.current
    )
}