package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingTextField(
    modifier: Modifier,
    subjectText: String,
    placeholder: String,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            Text(
                text = subjectText,
                color = colors.BLACK,
                style = typography.bodyLarge,
            )

            Spacer(modifier = modifier.height(8.dp))

            LectureDetailSettingTextField(
                modifier = modifier.fillMaxWidth(),
                placeholder = placeholder,
                subjectText = subjectText
            )
        }
    }
}