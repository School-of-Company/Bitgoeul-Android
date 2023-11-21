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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.R
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun BitgoeulApproveDialog(
    modifier: Modifier = Modifier,
    content: String,
    isVisible: Boolean,
) {
    var isDialogVisible = remember { isVisible }

    if (isDialogVisible) {
        BitgoeulAndroidTheme { colors, type ->
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
                        text = "신청 승인하시겠습니까?",
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
                                        isDialogVisible = !isDialogVisible
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
                                .weight(1f),
                        ) {
                            Text(
                                modifier = modifier
                                    .align(Alignment.Center)
                                    .padding(vertical = 13.dp)
                                    .clickable {
                                        isDialogVisible = !isDialogVisible
                                    },
                                text = stringResource(id = R.string.approve),
                                color = colors.WHITE,
                                style = type.bodySmall,
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
fun BitgoeulApproveDialogPre() {
    BitgoeulApproveDialog(
        content = "국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요 김하온 박주홍 강민수 이동욱 정찬교 이정우 김새미 서주미 정윤서 ",
        isVisible = true
    )
}