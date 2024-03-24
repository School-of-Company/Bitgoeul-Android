package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingInfoCard(
    modifier: Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Card(
                modifier = modifier
                    .background(color = colors.WHITE)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.WHITE)
            ) {
                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "박주홍",
                    color = colors.BLACK,
                    style = typography.labelLarge
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(text = "호남대학교", color = colors.G2, style = typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
fun ProfessorInChargeCardPre() {
    LectureDetailSettingInfoCard(modifier = Modifier)
}