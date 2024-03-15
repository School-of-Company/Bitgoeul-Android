package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun ProfessorInChargeList(
    modifier: Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier.background(color = Color.Transparent)
        ) {
            items(5) {
                Column(
                    modifier = modifier.background(color = Color.Transparent)
                ) {
                    ProfessorInChargeCard(
                        modifier = modifier.background(color = Color.Transparent)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(color = colors.G9)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfessorInChargeListPre() {
    ProfessorInChargeList(modifier = Modifier)
}