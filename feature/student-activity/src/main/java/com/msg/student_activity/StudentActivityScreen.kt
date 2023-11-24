package com.msg.student_activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import com.msg.ui.StudentActivityList
import java.util.UUID

@Composable
fun StudentActivityScreen(
    data: InquiryStudentActivityListResponse? = null,
    onAddClicked: () -> Unit,
    onItemClicked: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.background(colors.WHITE)
            ) {
                Spacer(modifier = Modifier
                    .height(20.dp)
                )
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {

                }
                Spacer(modifier = Modifier.height(8.dp))
                Column (
                    modifier = Modifier.padding(horizontal = 28.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "학생 활동",
                            style = typography.titleMedium,
                            color = colors.BLACK
                        )
                        IconButton(
                            onClick = onAddClicked,
                            content = { PlusIcon() }
                        )
                    }
                    if (data != null) {
                        StudentActivityList(data = data.content, onClick = onItemClicked)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StudentActivityScreenPre() {
    StudentActivityScreen(
        onAddClicked = {},
        onItemClicked = {}
    )
}