package com.msg.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.model.activity.InquiryStudentActivityModel
import java.util.UUID

@Composable
fun StudentActivityCard(
    data: InquiryStudentActivityModel,
    onClick: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(154.dp)
                .background(colors.WHITE)
        ) {
            Text(
                text = data.title,
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Text(
                text = data.activityId.toString(),  // 확정되면 변경예정 임시로 대충 넣어둠
                style = typography.bodySmall,
                color = colors.G1
            )
            Text(
                text = data.userName,
                style = typography.bodySmall,
                color = colors.G1
            )
        }
    }
}

@Preview
@Composable
fun StudentActivityCardPre() {
    StudentActivityCard(
        data = InquiryStudentActivityModel(
            activityId = UUID.randomUUID(),
            title = "title",
            userId = UUID.randomUUID(),
            userName = "name",
            approveStatus = ApproveStatus.APPROVED
        ),
        onClick = {}
    )
}