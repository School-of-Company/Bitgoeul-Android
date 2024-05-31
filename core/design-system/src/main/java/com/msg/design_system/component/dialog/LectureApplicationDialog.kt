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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.msg.design_system.R
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureApplicationDialog(
    modifier: Modifier = Modifier,
    content: String,
    isVisible: Boolean,
    onCancelClicked: () -> Unit,
    onConfirmClicked: () -> Unit,
) {
    if (isVisible) {
        BitgoeulAndroidTheme { colors, type ->
            Dialog(onDismissRequest = {
                onCancelClicked()
                onConfirmClicked()
            }) {
                Box(
                    modifier = modifier
                        .background(
                            color = colors.WHITE,
                            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(8.dp))
                        )
                        .wrapContentSize()
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            modifier = modifier.align(Alignment.CenterHorizontally),
                            text = "수강 신청하시겠습니까?",
                            color = colors.BLACK,
                            style = type.bodyLarge,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = modifier.height(16.dp))

                        ContentDescriptionText(
                            modifier = modifier.padding(horizontal = 16.dp),
                            maxLines = 3,
                            text = content
                        )

                        Spacer(modifier = modifier.height(16.dp))

                        Row(
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = modifier
                                    .clip(
                                        MaterialTheme.shapes.medium.copy(
                                            bottomStart = CornerSize(8.dp),
                                            bottomEnd = CornerSize(0.dp),
                                            topStart = CornerSize(0.dp),
                                            topEnd = CornerSize(0.dp)
                                        )
                                    )
                                    .background(
                                        color = colors.G2
                                    )
                                    .weight(1f)
                            ) {
                                Text(
                                    modifier = modifier
                                        .align(Alignment.Center)
                                        .padding(vertical = 13.dp)
                                        .clickable {
                                            onCancelClicked()
                                        },
                                    text = stringResource(id = R.string.cancel),
                                    color = colors.WHITE,
                                    style = type.bodySmall
                                )
                            }

                            Box(
                                modifier = modifier
                                    .clip(
                                        MaterialTheme.shapes.medium.copy(
                                            bottomStart = CornerSize(0.dp),
                                            bottomEnd = CornerSize(8.dp),
                                            topStart = CornerSize(0.dp),
                                            topEnd = CornerSize(0.dp)
                                        )
                                    )
                                    .background(
                                        color = colors.P5
                                    )
                                    .weight(1f)
                            ) {
                                Text(
                                    modifier = modifier
                                        .align(Alignment.Center)
                                        .padding(vertical = 13.dp)
                                        .clickable {
                                            onConfirmClicked()
                                        },
                                    text = stringResource(id = R.string.application),
                                    color = colors.WHITE,
                                    style = type.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
