package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingDepartmentCard(
    data: String,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Card(
                modifier = modifier
                    .background(color = colors.WHITE)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .clickable {
                        onClick(data)
                    },
                colors = CardDefaults.cardColors(containerColor = colors.WHITE)
            ) {
                Text(
                    text = data,
                    color = colors.BLACK,
                    style = typography.headlineMedium
                )
            }
        }
    }
}