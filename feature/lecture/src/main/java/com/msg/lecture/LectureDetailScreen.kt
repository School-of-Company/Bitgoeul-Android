package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailScreen() {
    BitgoeulAndroidTheme { colors, type ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }
    }
}

@Preview
@Composable
fun LectureDetailScreenPre() {
    LectureDetailScreen()
}