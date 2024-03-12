package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun LectureTypeTag(
    modifier: Modifier,
    lectureType: String,
) {
    var isSelected = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box(modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .border(
                width = 1.dp,
                color = if (isSelected.value) {
                    colors.P5
                } else {
                    colors.G2
                },
                shape = RoundedCornerShape(99.dp)
            )
            .background(
                color = if (isSelected.value) {
                    colors.P5
                } else {
                    colors.WHITE
                },
            )
            .clickable {
                isSelected.value = !isSelected.value
            }) {
            Text(
                text = lectureType,
                style = typography.bodyMedium,
                color = if (isSelected.value) {
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
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .size(200.dp)
    ) {

        LectureTypeTag(
            modifier = Modifier,
            lectureType = "상호학점인정교육과정"
        )
    }
}