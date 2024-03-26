package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.icon.DeleteIcon
import com.msg.design_system.component.picker.Picker
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureAttendanceDatePicker(
    modifier: Modifier,
) {
    BitgoeulAndroidTheme { colors, typography ->
        var rowItems by remember { mutableStateOf(listOf(1 to false)) }
        Surface(
            modifier = modifier.wrapContentSize()
        ) {
            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .background(Color.Cyan)
            ) {
                rowItems.forEachIndexed { _, (_, showDeleteIcon) ->
                    Row {
                        Picker(
                            modifier = modifier
                                .weight(0.7f)
                                .align(Alignment.CenterVertically),
                            text = stringResource(id = R.string.lecture_attendance_date)
                        )

                        if (showDeleteIcon) {
                            DeleteIcon(
                                modifier = modifier
                                    .weight(0.15f)
                                    .align(Alignment.CenterVertically)
                                    .clickable { rowItems = rowItems - (rowItems.size to true) },
                            )
                        }
                    }

                    Spacer(modifier = modifier.height(12.dp))

                    Row {
                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.start_semester_time)
                        )

                        Spacer(modifier = modifier.width(8.dp))

                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.end_time)
                        )
                    }
                }
                Text(
                    modifier = modifier.clickable {
                        rowItems = rowItems + (rowItems.size + 1 to true)
                    },
                    text = stringResource(id = R.string.add_date),
                    color = colors.G2,
                    style = typography.headlineMedium
                )
            }



            Spacer(modifier = modifier.height(28.dp))
        }
    }
}