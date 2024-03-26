package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureSettingTag(
    isSelected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    lectureType: String,
) {

    BitgoeulAndroidTheme { colors, typography ->
        Box(modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) {
                    colors.P5
                } else {
                    colors.G2
                },
                shape = RoundedCornerShape(99.dp)
            )
            .background(
                color = if (isSelected) {
                    colors.P5
                } else {
                    colors.WHITE
                },
            )
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }) {
            Text(
                text = lectureType,
                style = typography.bodyMedium,
                color = if (isSelected) {
                    colors.WHITE
                } else {
                    colors.G2
                },
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun LectureTypeTagPre() {
    val isLectureSettingTagSelected = remember{ mutableStateOf("0") }
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .wrapContentHeight()
    ) {

        LectureSettingTag(
            modifier = Modifier,
            lectureType = "상호학점인정교육과정",
            isSelected = isLectureSettingTagSelected.value == "0",
            onClick = { isLectureSettingTagSelected.value = "0" }
        )

        LectureSettingTag(
            modifier = Modifier,
            lectureType = "상호학점인정교육과정",
            isSelected = isLectureSettingTagSelected.value == "1",
            onClick = { isLectureSettingTagSelected.value = "1" }
        )

        LectureSettingTag(
            modifier = Modifier,
            lectureType = "상호학점인정교육과정",
            isSelected = isLectureSettingTagSelected.value == "2",
            onClick = { isLectureSettingTagSelected.value = "2" }
        )

        LectureSettingTag(
            modifier = Modifier,
            lectureType = "상호학점인정교육과정",
            isSelected = isLectureSettingTagSelected.value == "3",
            onClick = { isLectureSettingTagSelected.value = "3" }
        )

    }
}