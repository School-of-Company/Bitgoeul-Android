package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingScreen() {
    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE),
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
            ) {

            }
        }
    }
}

@Preview
@Composable
fun LectureDetailSettingScreenPre() {
    LectureDetailSettingScreen()
}