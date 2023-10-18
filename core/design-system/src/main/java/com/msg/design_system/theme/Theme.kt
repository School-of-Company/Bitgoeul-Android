package com.msg.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.msg.design_system.theme.color.Color
import com.msg.design_system.theme.color.ColorTheme


@Composable
fun BitgoeulAndroidTheme(
    colors: ColorTheme = Color,
    typography: Typography = Typography,
    content: @Composable (colors: ColorTheme, typography: Typography) -> Unit
) {
    content(colors, typography)
}