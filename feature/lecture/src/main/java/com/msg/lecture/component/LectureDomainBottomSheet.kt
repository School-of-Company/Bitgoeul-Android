package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.FileIcon
import com.msg.design_system.component.icon.RedCloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun LectureExcelDownloadBottomSheet(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onDownloadButtonClicked: () -> Unit,
    onQuit: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    BitgoeulAndroidTheme { colors, typography ->
        if (isVisible) {
            ModalBottomSheet(
                containerColor = colors.WHITE,
                sheetState = bottomSheetState,
                onDismissRequest = {
                    onQuit()
                },
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = colors.WHITE)
                        .padding(horizontal = 28.dp, vertical = 24.dp)
                ) {
                    Spacer(modifier = modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "강의 신청 명단 다운로드 (엑셀)",
                            style = typography.labelLarge,
                            color = colors.BLACK
                        )

                        Spacer(modifier.weight(1f))

                        FileIcon(
                            modifier = modifier.clickable {
                                coroutineScope.launch {
                                    onDownloadButtonClicked()
                                    bottomSheetState.hide()
                                }
                            }
                        )
                    }

                    Spacer(modifier = modifier.height(48.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "취소",
                            style = typography.labelLarge,
                            color = colors.BLACK
                        )

                        Spacer(modifier.weight(1f))

                        RedCloseIcon(
                            modifier = modifier.clickable {
                                coroutineScope.launch {
                                    onQuit()
                                    bottomSheetState.hide()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}