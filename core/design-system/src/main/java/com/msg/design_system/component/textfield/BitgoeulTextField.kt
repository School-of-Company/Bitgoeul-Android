package com.msg.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.CancelIcon
import com.msg.design_system.component.icon.SeeableIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.LastPasswordVisibleVisualTransformation

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
    onClickButton: () -> Unit,
    onClickLink: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    value: String? = null,
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
                        if (it.isFocused && onClick != null) onClick()
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
                                    onClickButton()
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
                                    onClickButton()
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
                )
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
                            if (onClickLink != null) {
                                LinkText(text = linkText, onClickLink = onClickLink)
                            }
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            if (onClickLink != null) {
                                LinkText(text = linkText, onClickLink = onClickLink)
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
    onClickLink: () -> Unit,
    isError: Boolean,
    isLinked: Boolean,
    isDisabled: Boolean,
    onClick: (() -> Unit)? = null,
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
                        if (it.isFocused && onClick != null) onClick()
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
                            LinkText(text = linkText, onClickLink = onClickLink)
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            LinkText(text = linkText, onClickLink = onClickLink)
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
    onClickButton: () -> Unit,
    onClick: (() -> Unit)? = null,
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
    onClickButton: () -> Unit,
    onClick: (() -> Unit)? = null,
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
    onClickLink: () -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(
            modifier = Modifier.clickable(enabled = true, onClick = onClickLink),
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
            onClickButton = {},
            isError = false,
            isLinked = false,
            isDisabled = false,
            isReadOnly = false,
            isReverseTrailingIcon = false,
            onClick = {}
        )

        PasswordTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Password",
            errorText = "Incorrect Password",
            onValueChange = {},
            onClickLink = {},
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
            onClickLink = {},
            isError = false,
            isLinked = false,
            isDisabled = true
        )

        InputTitleTextField(
            modifier = Modifier
                .width(304.dp)
                .wrapContentHeight(),
            placeholder = "강의 제목 (100자 이내)",
            onClick = {},
            onClickButton = {},
            onValueChange = {},
            maxTextLength = 100
        )
    }
}