package com.msg.club.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import java.util.UUID

@Composable
internal fun ClubMemberInfoItem(
    modifier: Modifier = Modifier,
    data: String,
    isTeacher: Boolean,
    id: UUID,
    onItemClicked: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .padding(vertical = 8.dp)
                .clickable {
                    onItemClicked(id)
                }
        ) {
            Text(
                text = data,
                style = typography.labelMedium,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = if (isTeacher) "담당 선생님" else "학생",
                style = typography.labelMedium,
                color = colors.G2
            )
        }
    }
}