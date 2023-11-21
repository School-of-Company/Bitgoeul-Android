package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.InputTitleTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureOpenScreen(
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE),
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
                    onClick = { onBackClick() }
                )

                Spacer(modifier = Modifier.height(17.dp))

                LectureOpenScreenContent()
            }
        }
    }
}

@Composable
fun LectureOpenScreenContent() {
    BitgoeulAndroidTheme { colors, type ->
        Column(
            modifier = Modifier
                .background(color = colors.WHITE)
                .wrapContentSize()
                .padding(horizontal = 24.dp)
        ) {
            InputTitleTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeholder = "강의 제목 (100자 이내)",
                onClick = {},
                onClickButton = {},
                onValueChange = {},
                maxTextLength = 100
            )
        }
    }
}

@Preview
@Composable
fun LectureOpenScreenPre() {
    LectureOpenScreen(onBackClick = {})
}