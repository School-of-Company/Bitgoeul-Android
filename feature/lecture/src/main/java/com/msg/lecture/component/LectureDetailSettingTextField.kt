package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                        selectorBottomSheet = true,
                        searchBottomSheet = false
                    )
                }

                "Search" -> {
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
                        placeholder = selectedItem,
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
    BitgoeulAndroidTheme { colors, _ ->
        Column(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(8.dp))

            LectureDetailSettingTextField(
                modifier = modifier.fillMaxWidth(),
                list = listOf(),
                selectedItem = placeholder,
                onItemChange = {},
                selectorBottomSheet = false,
                searchBottomSheet = true
            )
        }
    }
}

@Composable
fun LectureDetailSettingLectureDatesTextField(
    modifier: Modifier,
    placeholder: String,
    onClick: () -> Unit,
) {

}