package com.msg.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun GetClubListDialog(
    modifier: Modifier = Modifier,
    onQuit: (Boolean) -> Unit,
    isVisible: Boolean,
    onItemClicked: (String) -> Unit,
    list: List<String>
) {
    if (isVisible) {
        BitgoeulAndroidTheme { colors, typography ->
            Dialog(
                onDismissRequest = { onQuit(false) },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                Box(
                    modifier = modifier
                        .background(
                            color = colors.WHITE,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .width(288.dp)
                        .height(480.dp),
                ) {
                    Column(
                        modifier = modifier
                            .padding(horizontal = 24.dp)
                    ) {
                        Spacer(modifier = modifier.height(24.dp))
                        Row(
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "학교선택",
                                style = typography.titleSmall,
                                color = colors.BLACK
                            )
                            Spacer(modifier = modifier.weight(1f))
                            IconButton(
                                modifier = modifier
                                    .height(24.dp)
                                    .width(24.dp),
                                onClick = { onQuit(false) },
                                content = { CloseIcon() }
                            )
                        }
                        Spacer(modifier = modifier.height(24.dp))
                        LazyColumn(
                            modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(list.size) {
                                Spacer(modifier = modifier.height(16.dp))
                                Text(
                                    modifier = modifier.clickable {
                                        onItemClicked(list[it])
                                        onQuit(false)
                                    },
                                    text = list[it],
                                    style = typography.bodySmall,
                                    color = colors.BLACK
                                )
                                Spacer(modifier = modifier.height(16.dp))
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = colors.G9
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GetClubListDialogPre() {
    GetClubListDialog(
        onQuit = {},
        onItemClicked = {},
        list = listOf(),
        isVisible = true
    )
}