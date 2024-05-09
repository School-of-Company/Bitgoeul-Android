package com.msg.certification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.response.lecture.SignUpLectures
import com.msg.ui.util.toKoreanFormat

@Composable
fun FinishedLectureItem(
    modifier: Modifier = Modifier,
    data: SignUpLectures
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "# ${data.lectureType}",
                style = typography.labelMedium,
                color = colors.P3
            )
            Text(
                text = data.name,
                style = typography.bodyMedium,
                color = colors.BLACK
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${data.lecturer}교수님",
                    style = typography.labelMedium,
                    color = colors.G1
                )
                VerticalDivider(
                    modifier = modifier.height(14.dp),
                    thickness = 1.dp,
                    color = colors.G1
                )
                Text(
                    text = if (data.isComplete) "${data.currentCompletedDate.toKoreanFormat()} 이수" else "미이수",
                    style = typography.labelMedium,
                    color = colors.G1
                )
            }
        }
    }
}