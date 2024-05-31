package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.design_system.component.bottomsheet.SelectedIndicator
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
internal fun LectureFilterDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onCloseButtonClick: () -> Unit,
    onFilterButtonClick: (String?) -> Unit?,
) {
    val isFilterSelected = remember { mutableStateOf("0") }
    if (isVisible) {
        BitgoeulAndroidTheme { colors, typography ->
            Dialog(onDismissRequest = { onCloseButtonClick() }) {
                Box(
                    modifier = modifier
                        .background(color = colors.WHITE, RoundedCornerShape(8.dp))
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = modifier.padding(horizontal = 24.dp, vertical = 24.dp)
                    ) {
                        Row {
                            Text(
                                text = stringResource(id = com.msg.design_system.R.string.lecture_list),
                                style = typography.titleSmall,
                                color = colors.BLACK,
                            )

                            Spacer(modifier = modifier.weight(1f))

                            CloseIcon(
                                modifier = modifier.clickable {
                                    onCloseButtonClick()
                                }
                            )
                        }

                        Spacer(modifier = modifier.height(24.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SelectedIndicator(
                                modifier = modifier.size(20.dp),
                                isSelected = isFilterSelected.value == "0",
                                onClick = {
                                    onFilterButtonClick(null)
                                    isFilterSelected.value = "0"
                                }
                            )

                            Spacer(modifier = modifier.width(8.dp))

                            Text(
                                text = stringResource(id = com.msg.design_system.R.string.entire),
                                style = typography.bodySmall,
                                color = colors.BLACK
                            )
                        }

                        Spacer(modifier = modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SelectedIndicator(
                                modifier = modifier.size(20.dp),
                                isSelected = isFilterSelected.value == "1",
                                onClick = {
                                    onFilterButtonClick("상호학점인정교육과정")
                                    isFilterSelected.value = "1"
                                }
                            )

                            Spacer(modifier = modifier.width(8.dp))

                            Text(
                                text = stringResource(id = com.msg.design_system.R.string.mutual_credit_recognition_curriculum),
                                style = typography.bodySmall,
                                color = colors.BLACK
                            )
                        }

                        Spacer(modifier = modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SelectedIndicator(
                                modifier = modifier.size(20.dp),
                                isSelected = isFilterSelected.value == "2",
                                onClick = {
                                    onFilterButtonClick("대학탐방프로그램")
                                    isFilterSelected.value = "2"
                                }
                            )

                            Spacer(modifier = modifier.width(8.dp))

                            Text(
                                text = stringResource(id = com.msg.design_system.R.string.university_visit_program),
                                style = typography.bodySmall,
                                color = colors.BLACK
                            )
                        }
                    }
                }
            }
        }
    }
}