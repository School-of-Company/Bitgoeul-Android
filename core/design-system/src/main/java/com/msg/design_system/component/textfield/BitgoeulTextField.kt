package com.msg.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.bottomsheet.DatePickerBottomSheet
import com.msg.design_system.component.bottomsheet.SelectorBottomSheet
import com.msg.design_system.component.bottomsheet.TimePickerBottomSheet
import com.msg.design_system.component.icon.CancelIcon
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.component.icon.SearchIcon
import com.msg.design_system.component.icon.SeeableIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.LastPasswordVisibleVisualTransformation
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    modifier: Modifier,
    placeholder: String,
    isReadOnly: Boolean = false,
    isNumberOnly: Boolean = false,
    isError: Boolean,
    isLinked: Boolean,
    isDisabled: Boolean,
    isReverseTrailingIcon: Boolean,
    errorText: String,
    linkText: String? = null,
    onValueChange: (String) -> Unit,
    onButtonClicked: () -> Unit,
    onLinkClicked: (() -> Unit)? = null,
    onClicked: (() -> Unit)? = null,
    value: String? = null,
    visualTransformationState: Boolean = false,
) {
    var text by remember { mutableStateOf(value ?: "") }
    val isFocused = remember { mutableStateOf(false) }
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = value ?: it
                    onValueChange(it)
                },
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = when {
                            isDisabled -> colors.G1
                            isError -> colors.E5
                            isFocused.value -> colors.P5
                            text.isNotEmpty() -> colors.G1
                            else -> colors.G1
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                        if (it.isFocused && onClicked != null) onClicked()
                        if (!it.isFocused && value != null) text = value
                    }
                    .background(
                        color = if (isDisabled) colors.G9 else Color.Transparent
                    ),
                textStyle = typography.bodySmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedPlaceholderColor = colors.G2,
                    unfocusedPlaceholderColor = colors.G2,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = if (isError) colors.E5 else colors.BLACK,
                    unfocusedTextColor = if (isError) colors.E5 else colors.BLACK,
                    disabledTextColor = colors.G1,
                    cursorColor = colors.P5
                ),
                enabled = !isDisabled,
                placeholder = {
                    Text(text = placeholder, style = typography.bodySmall)
                },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    if (!isReverseTrailingIcon) {
                        if (isFocused.value) {
                            IconButton(
                                onClick = {
                                    text = ""
                                    onButtonClicked()
                                },
                                enabled = text.isNotEmpty()
                            ) {
                                if (text.isNotEmpty()) CancelIcon()
                            }
                        }
                    } else {
                        if (!isFocused.value) {
                            IconButton(
                                onClick = {
                                    text = ""
                                    onButtonClicked()
                                },
                                enabled = text.isNotEmpty()
                            ) {
                                if (text.isNotEmpty()) CancelIcon()
                            }
                        }
                    }
                },
                readOnly = isReadOnly,
                keyboardOptions = if (isNumberOnly) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
                    autoCorrect = false
                ),
                visualTransformation = if (visualTransformationState) PasswordVisualTransformation() else VisualTransformation.None
            )
            if (isError || isLinked) {
                val isAll: Boolean = isError && isLinked
                Row(
                    modifier = modifier.padding(4.dp),
                    horizontalArrangement = if (isAll) Arrangement.SpaceBetween else if (isError) Arrangement.Start else Arrangement.End
                ) {
                    if (isAll) {
                        ErrorText(text = errorText)
                        if (linkText != null) {
                            if (onLinkClicked != null) {
                                LinkText(text = linkText, onLinkClicked = onLinkClicked)
                            }
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            if (onLinkClicked != null) {
                                LinkText(text = linkText, onLinkClicked = onLinkClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier,
    placeholder: String,
    errorText: String,
    linkText: String? = null,
    onValueChange: (String) -> Unit,
    onLinkClicked: () -> Unit,
    isError: Boolean,
    isLinked: Boolean,
    isDisabled: Boolean,
    onClicked: (() -> Unit)? = null,
) {
    var showPassword by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    val isChanged = remember { mutableStateOf(false) }
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                    isChanged.value = true
                },
                placeholder = {
                    Text(text = placeholder, style = typography.bodySmall)
                },
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = when {
                            isDisabled -> colors.G1
                            isError -> colors.E5
                            text.isNotEmpty() -> colors.G1
                            isFocused.value -> colors.P5
                            else -> colors.G1
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                        if (it.isFocused && onClicked != null) onClicked()
                        if (it.isFocused) isChanged.value = false
                    }
                    .background(
                        color = if (isDisabled) colors.G9 else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 1,
                singleLine = true,
                textStyle = typography.bodySmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedPlaceholderColor = colors.G2,
                    unfocusedPlaceholderColor = colors.G2,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = if (isError) colors.E5 else colors.BLACK,
                    unfocusedTextColor = if (isError) colors.E5 else colors.BLACK,
                    disabledTextColor = colors.G1,
                    cursorColor = colors.P5
                ),
                enabled = !isDisabled,
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    LastPasswordVisibleVisualTransformation(
                        isFocused = isFocused.value,
                        isChanged = isChanged.value
                    )
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            SeeableIcon(modifier = modifier, disable = !showPassword)
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            SeeableIcon(modifier = modifier, disable = !showPassword)
                        }
                    }
                }
            )
            if (isError || isLinked) {
                val isAll: Boolean = isError && isLinked
                Row(
                    modifier = modifier.padding(4.dp),
                    horizontalArrangement = if (isAll) Arrangement.SpaceBetween else if (isError) Arrangement.Start else Arrangement.End
                ) {
                    if (isAll) {
                        ErrorText(text = errorText)
                        if (linkText != null) {
                            LinkText(text = linkText, onLinkClicked = onLinkClicked)
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            LinkText(text = linkText, onLinkClicked = onLinkClicked)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTitleTextField(
    modifier: Modifier,
    placeholder: String,
    maxTextLength: Int,
    onValueChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    BitgoeulAndroidTheme { colors, type ->
        Column(
            modifier = modifier.background(color = Color.Transparent),
        ) {
            TextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxTextLength) {
                        text = it
                        onValueChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                placeholder = {
                    Text(text = placeholder, style = type.titleSmall, color = colors.G1)
                },
                textStyle = type.titleSmall.copy(color = colors.BLACK),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P5
                ),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputMainContentTextField(
    modifier: Modifier,
    placeholder: String,
    maxTextLength: Int,
    onValueChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    BitgoeulAndroidTheme { colors, type ->
        Column(
            modifier = modifier.background(color = Color.Transparent),
        ) {
            TextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxTextLength) {
                        text = it
                        onValueChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                placeholder = {
                    Text(text = placeholder, style = type.bodySmall, color = colors.G1)
                },
                textStyle = type.bodySmall.copy(color = colors.BLACK),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P5
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailingIconTextField(
    modifier: Modifier,
    placeholder: String,
    isDisabled: Boolean,
    onValueChange: (String) -> Unit,
    onButtonClicked: () -> Unit,
    value: String? = null,
) {
    var text by remember { mutableStateOf(value ?: "") }

    BitgoeulAndroidTheme { colors, typography ->
        Box {
            OutlinedTextField(
                placeholder = {
                    Text(text = placeholder, style = typography.bodySmall, color = colors.G2)
                },
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                modifier = modifier
                    .border(1.dp, shape = RoundedCornerShape(8.dp), color = colors.G1)
                    .background(
                        color = Color.Transparent
                    ),
                textStyle = typography.bodySmall.copy(color = colors.BLACK),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedPlaceholderColor = colors.G2,
                    unfocusedPlaceholderColor = colors.G2,
                    focusedTextColor = colors.BLACK,
                    unfocusedTextColor = colors.BLACK,
                    disabledTextColor = colors.G1,
                    cursorColor = colors.P5,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                enabled = !isDisabled,
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = onButtonClicked) {
                        SearchIcon(
                            modifier = modifier
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun LectureDetailSettingTextField(
    modifier: Modifier = Modifier,
    list: List<String>,
    selectedItem: String,
    onItemChange: (item: String) -> Unit,
    isLectureType: Boolean = false,
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
                },
        ) {
            Text(
                text = selectedItem,
                style = typography.bodySmall,
                color = colors.G2
            )

            if (isFocused.value) {
                SelectorBottomSheet(
                    list = list,
                    selectedItem = selectedItem,
                    itemChange = onItemChange,
                    onQuit = {
                        isFocused.value = false
                    },
                    firstItem = {},
                    lastItem = {
                        if (isLectureType) {
                            Column {
                                Text(
                                    text = "기타",
                                    style = typography.bodyMedium,
                                    color = colors.BLACK
                                )
                                Text(
                                    text = "직접 작성하기",
                                    style = typography.labelMedium,
                                    color = colors.G2
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PickerTextField(
    modifier: Modifier = Modifier,
    text: String,
    list: List<String>,
    selectedItem: String,
    onItemChange: (item: String) -> Unit,
    isDatePicker: Boolean = false,
    isTimePicker: Boolean = false,
    onDatePickerQuit: (LocalDate?) -> Unit = {},
    onTimePickerQuit: (LocalTime?) -> Unit = {},
) {
    val isFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = if (isFocused.value) colors.P5 else colors.G9,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isFocused.value = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                color = if (isFocused.value) colors.WHITE else colors.G2,
                style = type.bodySmall,
            )

            PickerArrowIcon(isSelected = isFocused.value)

            if (isFocused.value && isDatePicker) {
                DatePickerBottomSheet {
                    isFocused.value = false
                    onDatePickerQuit(it)
                }
            } else if (isFocused.value && isTimePicker) {
                TimePickerBottomSheet {
                    isFocused.value = false
                    onTimePickerQuit(it)
                }
            } else if (isFocused.value) {
                SelectorBottomSheet(
                    list = list,
                    selectedItem = selectedItem,
                    itemChange = onItemChange,
                    onQuit = {
                        isFocused.value = false
                    },
                    firstItem = {}
                )
            }
        }
    }
}


@Composable
fun ErrorText(
    text: String,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(text = text, color = colors.E5, style = typography.labelMedium)
    }
}

@Composable
fun LinkText(
    text: String,
    onLinkClicked: () -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(
            modifier = Modifier.clickable(enabled = true, onClick = onLinkClicked),
            text = text,
            color = colors.P5,
            style = typography.labelMedium
        )
    }
}

@Preview
@Composable
fun TextFieldPre() {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        DefaultTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Email",
            errorText = "Incorrect Format",
            onValueChange = {},
            onButtonClicked = {},
            isError = false,
            isLinked = false,
            isDisabled = false,
            isReadOnly = false,
            isReverseTrailingIcon = false,
            onClicked = {},
            onLinkClicked = {}
        )

        PasswordTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Password",
            errorText = "Incorrect Password",
            onValueChange = {},
            onLinkClicked = {},
            isError = true,
            isLinked = true,
            linkText = "Sign Up",
            isDisabled = false
        )

        PasswordTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Password",
            errorText = "Incorrect Password",
            onValueChange = {},
            onLinkClicked = {},
            isError = false,
            isLinked = false,
            isDisabled = true
        )

        InputTitleTextField(
            modifier = Modifier
                .width(304.dp)
                .wrapContentHeight(),
            placeholder = "강의 제목 (100자 이내)",
            onValueChange = {},
            maxTextLength = 100
        )
        TrailingIconTextField(
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = "Search",
            onValueChange = {},
            isDisabled = false,
            onButtonClicked = {}
        )

        PickerTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = "강의 날짜 선택",
            list = listOf(),
            selectedItem = "",
            onItemChange = {},
            isDatePicker = true
        )
    }
}