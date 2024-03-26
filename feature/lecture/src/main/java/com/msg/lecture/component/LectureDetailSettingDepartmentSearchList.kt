package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import java.util.UUID

@Composable
fun LectureDetailSettingDepartmentSearchList(
    modifier: Modifier,
    onClick: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            items(5) {
                LectureDetailSettingDepartmentCard(
                    modifier = modifier,
                    onClick = onClick
                )

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = colors.G9)
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureDetailSettingDepartmentSearchListPre() {
    LectureDetailSettingDepartmentSearchList(
        modifier = Modifier,
        onClick = {}
    )
}