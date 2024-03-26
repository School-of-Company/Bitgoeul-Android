package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.model.lecture.SearchResponseModel
import java.util.UUID

@Composable
fun LectureDetailSettingDepartmentCard(
    data: SearchResponseModel?,
    modifier: Modifier,
    onClick: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.WHITE)
            ) {
                Text(
                    text = data?.lines.toString(),
                    color = colors.BLACK,
                    style = typography.headlineMedium
                )
            }
        }
    }
}