package com.msg.post

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.ui.DevicePreviews

@Composable
fun PostAddScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    savedTitle: String,
    savedContent: String
) {
    val title = remember { mutableStateOf(savedTitle) }
    val content = remember { mutableStateOf(savedContent) }
    
    val maxTitleLength = 100

    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier.fillMaxSize()
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
                        .padding(horizontal = 28.dp)
                        .verticalScroll(scrollState)
                        .weight(1f)
                ) {
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
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
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = content.value,
                        onValueChange = { if (it.length <= maxTitleLength) title.value = it },
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
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    DetailSettingButton(
                        modifier = modifier.fillMaxWidth(),
                        type = "게시글"
                    ) {
                        onSettingClicked()
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    BitgoeulButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "게시글 추가",
                        state = if (title.value.isNotEmpty() && content.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
                    ) {
                    }
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun PostAddScreenPre() {
    PostAddScreen(
        onBackClicked = {},
        onSettingClicked = {},
        savedTitle = "",
        savedContent = ""
    )
}