package com.msg.club.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.club.ClubDetailResponse

@Composable
fun ClubInfoSection(
    modifier: Modifier = Modifier,
    data: ClubDetailResponse
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = data.clubName,
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Row {
                Text(
                    text = "소속학교",
                    style = typography.bodySmall,
                    color = colors.P5
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = data.highSchoolName,
                    style = typography.bodySmall,
                    color = colors.P5
                )
            }
            Row {
                Text(
                    text = "담당 선생님",
                    style = typography.bodySmall,
                    color = colors.G2
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = data.teacher.name,
                    style = typography.bodySmall,
                    color = colors.G2
                )
            }
        }
    }
}