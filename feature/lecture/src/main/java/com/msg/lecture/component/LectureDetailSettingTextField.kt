package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.LectureDetailSettingTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailSettingSection(
    modifier: Modifier,
    subjectText: String,
    list: List<String>,
    selectedItem: String,
    onItemChange: (item: String) -> Unit,
    type: String,
    onClick: () -> Unit = {},
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            if (subjectText.isNotEmpty()) {
                Text(
                    text = subjectText,
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )
            }

            when (type) {
                "Selector" -> {
                    Spacer(modifier = modifier.height(8.dp))

                    LectureDetailSettingTextField(
                        modifier = modifier.fillMaxWidth(),
                        list = list,
                        selectedItem = selectedItem,
                        onItemChange = onItemChange,
                    )
                }

                "Search" -> {
                    Spacer(modifier = modifier.height(8.dp))

                    LectureDetailSettingSearchTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = selectedItem,
                        onClick = onClick
                    )
                }

                "Input" -> {
                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = selectedItem,
                        onItemChange = { inputString ->
                            onItemChange(inputString)
                        }
                    )
                }

                "LectureDates" -> {
                    LectureDetailSettingLectureDatesTextField(
                        modifier = modifier.fillMaxWidth(),
                        selectedItem = selectedItem,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun LectureDetailSettingInputTextField(
    modifier: Modifier,
    placeholder: String,
    onItemChange: (item: String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, _ ->
        Column(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(8.dp))

            DefaultTextField(
                modifier = modifier.fillMaxWidth(),
                placeholder = placeholder,
                errorText = "",
                isError = false,
                isDisabled = false,
                isLinked = false,
                isReverseTrailingIcon = false,
                onValueChange = { inputString ->
                    onItemChange(inputString)
                },
                onClickButton = {},
            )
        }
    }
}

@Composable
fun LectureDetailSettingSearchTextField(
    modifier: Modifier,
    placeholder: String,
    onClick: () -> Unit,
) {
    val isFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isFocused.value) colors.P5 else colors.G1,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = colors.WHITE)
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isFocused.value = true
                    onClick()
                },
        ) {
            Text(
                text = placeholder,
                style = typography.bodySmall,
                color = colors.G2
            )
        }
    }
}

@Composable
fun LectureDetailSettingLectureDatesTextField(
    modifier: Modifier,
    selectedItem: String,
    onClick: () -> Unit,
) {
    val isFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isFocused.value) colors.P5 else colors.G1,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = colors.WHITE)
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isFocused.value = true
                    onClick()
                },
        ) {
            Text(
                text = selectedItem,
                style = typography.bodySmall,
                color = colors.G2
            )
        }
    }
}