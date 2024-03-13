package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.design_system.R
import com.msg.design_system.component.icon.BlackCloseIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.TrailingIconTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun SelectProfessorDialog(
    modifier: Modifier,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = modifier
                    .background(color = colors.WHITE, RoundedCornerShape(8.dp))
                    .padding(horizontal = 24.dp)
                    .wrapContentSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.select_professor_in_charge),
                        color = colors.BLACK,
                        style = typography.labelSmall,
                        modifier = modifier
                            .weight(0.7f)
                    )
                    BlackCloseIcon(
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                TrailingIconTextField(
                    modifier = modifier
                        .padding(top = 80.dp)
                        .width(320.dp)
                        .height(52.dp),
                    placeholder = "이름 또는 학교명으로 검색...",
                    onValueChange = {},
                    isDisabled = false,
                    onClick = {},
                    onClickButton = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectProfessorDialogPre() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        SelectProfessorDialog(modifier = Modifier)
    }
}