package com.msg.certification.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.club.StudentBelongClubEntity

@Composable
fun StudentInfoSection(
    modifier: Modifier = Modifier,
    data: StudentBelongClubEntity
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = data.name,
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(4.dp))
            Row {
                Text(
                    text = "총학점",
                    style = typography.bodySmall,
                    color = colors.P5
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = data.credit.toString(),
                    style = typography.bodySmall,
                    color = colors.P5
                )
            }
            Spacer(modifier = modifier.height(5.dp))
            Row {
                Text(
                    text = "이메일",
                    style = typography.labelMedium,
                    color = colors.G2
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = data.email,
                    style = typography.labelMedium,
                    color = colors.G2
                )
            }
            Spacer(modifier = modifier.height(5.dp))
            Row {
                Text(
                    text = "전화번호",
                    style = typography.labelMedium,
                    color = colors.G2
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = data.phoneNumber,
                    style = typography.labelMedium,
                    color = colors.G2
                )
            }
        }
    }
}

@Preview
@Composable
fun StudentInfoSectionPre() {
    StudentInfoSection(
        data = StudentBelongClubEntity(
            name = "채종인",
            phoneNumber = "010-1234-5678",
            email = "s22055@gsm.hs.kr",
            credit = 300
        )
    )
}