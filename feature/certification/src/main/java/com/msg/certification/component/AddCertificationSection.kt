package com.msg.certification.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun AddCertificationSection(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "자격증",
                style = typography.titleSmall,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(8.dp))
            DefaultTextField(
                modifier = modifier.fillMaxWidth(),
                placeholder = "자격증 이름 입력",
                isError = false,
                isLinked = false,
                isDisabled = false,
                isReverseTrailingIcon = false,
                errorText = "",
                onValueChange = onValueChange,
                onClickButton = onClickButton
            )
        }
    }
}