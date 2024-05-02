package com.msg.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun BitgoeulAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    msg: String,
    onQuit: () -> Unit,
    onButtonClicked: () -> Unit
) {
    val isVisible = remember { mutableStateOf(true) }
    if (isVisible.value) {
        Dialog(
            onDismissRequest = onQuit,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true
            )
        ) {
            BitgoeulAndroidTheme { colors, typography ->
                Box(
                    modifier = modifier
                        .background(
                            color = colors.WHITE,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(top = 16.dp)
                ) {
                    Column {
                        Text(
                            modifier = modifier.align(Alignment.CenterHorizontally),
                            text = title,
                            color = colors.BLACK,
                            style = typography.bodyLarge
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        ContentDescriptionText(
                            modifier = modifier.padding(horizontal = 16.dp),
                            maxLines = 3,
                            text = msg
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    onButtonClicked()
                                    onQuit()
                                    isVisible.value = false
                                }
                                .clip(
                                    MaterialTheme.shapes.medium.copy(
                                        bottomStart = CornerSize(8.dp),
                                        bottomEnd = CornerSize(8.dp),
                                        topStart = CornerSize(0.dp),
                                        topEnd = CornerSize(0.dp)
                                    )
                                )
                                .background(color = colors.P5)
                                .padding(vertical = 13.dp)
                        ) {
                            Text(
                                modifier = modifier.align(Alignment.Center),
                                text = "확인",
                                style = typography.bodySmall,
                                color = colors.WHITE
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BitgoeulAlertDialogPre() {
    BitgoeulAlertDialog(
        onQuit = {},
        title = "오류",
        msg = "로그인 시간이 만료되었습니다. 다시 로그인해주세요",
        onButtonClicked = {}
    )
}