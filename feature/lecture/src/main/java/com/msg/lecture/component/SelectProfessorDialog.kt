package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
                        .wrapContentSize()
                ) {

                    Row(
                        modifier = modifier.padding(vertical = 24.dp, horizontal = 24.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.select_professor_in_charge),
                            color = colors.BLACK,
                            style = typography.bodyLarge,
                        )
                    }
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