package com.msg.student_activity

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun AddActivityScreen() {

    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }

    val maxTitleLength = 100
    val maxContentLength = 1000

    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {

                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .verticalScroll(scrollState)
                        .weight(1f)
                ) {
                    BasicTextField(
                        modifier = Modifier
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
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    DetailSettingButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        type = "활동"
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    BitgoeulButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "강의 개설 신청",
                        state = if (title.value.isNotEmpty() && content.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
                    ) {

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun AddActivityScreenPre() {
    AddActivityScreen()
}