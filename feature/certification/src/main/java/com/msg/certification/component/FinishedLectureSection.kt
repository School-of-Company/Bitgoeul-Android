package com.msg.certification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.entity.lecture.SignUpLectures
import java.time.LocalDate
import java.util.UUID

@Composable
fun FinishedLectureSection(
    modifier: Modifier = Modifier,
    data: GetLectureSignUpHistoryEntity
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            Text(
                text = "이수한 강의 목록",
                style = typography.titleSmall,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(24.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(data.lectures) {
                    FinishedLectureItem(data = it)
                    Spacer(modifier = modifier.height(24.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = colors.G9
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FinishedLectureSectionPre() {
    FinishedLectureSection(
        data = GetLectureSignUpHistoryEntity(
            lectures = listOf(
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "개쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                ),
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "덜쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                )
            )
        )
    )
}