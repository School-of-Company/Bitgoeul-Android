package com.msg.design_system.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class LastPasswordVisibleVisualTransformation(
    private val mask: Char = '\u2022',
    private val isFocused: Boolean,
    private val isChanged: Boolean
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val isFocusedAgainState: Boolean = if (isChanged) isFocused else !isFocused
        return TransformedText(
            text = if (isFocusedAgainState) {
                if (text.text.isNotEmpty()) {
                    AnnotatedString(mask.toString().repeat(text.text.length - 1) + text.text.last())
                } else {
                    AnnotatedString(mask.toString().repeat(text.text.length))
                }
            } else {
                AnnotatedString(mask.toString().repeat(text.text.length))
            },
            offsetMapping = OffsetMapping.Identity
        )
    }
}