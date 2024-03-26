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
import java.util.UUID

@Composable
fun LectureDetailSettingDepartmentCard(
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
                    text = "A.I융합기계과(야)",
                    color = colors.BLACK,
                    style = typography.headlineMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun test() {
    LectureDetailSettingDepartmentCard(
        modifier = Modifier,
        onClick = {}
    )
}