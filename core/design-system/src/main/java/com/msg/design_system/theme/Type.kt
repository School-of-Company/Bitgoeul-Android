package com.msg.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.msg.design_system.R

val pretendard = FontFamily(
    Font(R.font.pretendard_regular),
    Font(R.font.pretendard_semibold)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 36.sp
    ),
    titleMedium = TextStyle(
        fontFamily = pretendard,
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 31.sp
    ),
    titleSmall = TextStyle(
        fontFamily = pretendard,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 25.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendard,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = pretendard,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    ),
    labelMedium = TextStyle(
        fontFamily = pretendard,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
)