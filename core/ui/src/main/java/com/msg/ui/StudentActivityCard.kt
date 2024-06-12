package com.msg.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.enumdata.ApproveStatus
import com.msg.model.enumdata.Authority
import com.msg.model.model.activity.GetStudentActivityModel
import com.msg.ui.util.toKoreanFormat
import java.time.LocalDate
import java.util.UUID

@Composable
fun StudentActivityCard(
    data: GetStudentActivityModel,
    onClick: (UUID) -> Unit,
    role: Authority = Authority.ROLE_USER
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(154.dp)
                .clickable(
                    onClick = { onClick(data.activityId) }
                ),
            colors = CardDefaults.cardColors(
                containerColor = colors.WHITE
            ),
            border = BorderStroke(1.dp, colors.G9)
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = data.title,
                        style = typography.bodyLarge,
                        color = colors.BLACK
                    )
                    Text(
                        text = data.activityDate.toKoreanFormat(),
                        style = typography.bodySmall,
                        color = colors.G1
                    )
                    Text(
                        text = data.userName,
                        style = typography.bodySmall,
                        color = colors.G1
                    )
                }
                if (role == Authority.ROLE_TEACHER) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(
                                color = if (data.approveStatus == ApproveStatus.APPROVED) colors.P5 else colors.E5,
                                shape = RoundedCornerShape(18.dp),
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = if (data.approveStatus == ApproveStatus.APPROVED) "승인됨" else "승인 대기 중",
                        style = typography.labelMedium,
                        color = colors.WHITE
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StudentActivityCardPre() {
    StudentActivityCard(
        data = GetStudentActivityModel(
            activityId = UUID.randomUUID(),
            title = "title",
            activityDate = LocalDate.now(),
            userId = UUID.randomUUID(),
            userName = "name",
            approveStatus = ApproveStatus.APPROVED
        ),
        onClick = {}
    )
}