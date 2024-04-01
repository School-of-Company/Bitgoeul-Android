package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingDepartmentSearchList(
    data: SearchResponseModel,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            items(data.lines.size) {
                    LectureDetailSettingDepartmentCard(
                        modifier = modifier,
                        onClick = onClick,
                        data = data.lines[it]
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