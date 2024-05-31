package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
internal fun LectureSettingTag(
    isSelected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    text: String,
) {
    val interactionSource = remember { mutableStateOf(MutableInteractionSource()) }
    BitgoeulAndroidTheme { colors, typography ->
        Box(modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) colors.P5 else colors.G2
                ,
                shape = RoundedCornerShape(99.dp)
            )
            .background(
                color = if (isSelected) colors.P5 else colors.WHITE
                ,
            )
            .clickable(
                interactionSource = interactionSource.value,
                indication = rememberRipple(bounded = false)
            ) {
                if (onClick != null) {
                    onClick()
                }
            }) {
            Text(
                text = text,
                style = typography.bodyMedium,
                color = if (isSelected) colors.WHITE else colors.G2
                ,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

        }
    }
}