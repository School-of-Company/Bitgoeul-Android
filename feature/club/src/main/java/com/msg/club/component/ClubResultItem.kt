package com.msg.club.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.DashIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.club.ClubListResponse

@Composable
fun ClubResultItem(
    modifier: Modifier = Modifier,
    data: ClubListResponse,
    onItemClicked: (Long) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = data.name,
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(12.dp))
            Row(
                modifier = modifier.clickable {
                    onItemClicked(data.id)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "내부 인원 보기",
                    style = typography.labelMedium,
                    color = colors.G1
                )
                Spacer(modifier = modifier.width(6.dp))
                IconButton(
                    modifier = modifier
                        .height(10.dp)
                        .width(6.dp),
                    onClick = {},
                    content = { DashIcon() }
                )
            }
        }
    }
}

@Preview
@Composable
fun ClubResultItemPre() {
    ClubResultItem(
        data = ClubListResponse(
            id = 12,
            name = "HMI동아리",
            schoolName = "광주자동화설비공업고등학교"
        ),
        onItemClicked = {}
    )
}