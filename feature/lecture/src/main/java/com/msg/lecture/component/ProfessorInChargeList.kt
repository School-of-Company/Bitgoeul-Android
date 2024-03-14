package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun ProfessorInChargeList(
    modifier: Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn {
            items(5) {
                Column {
                    ProfessorInChargeCard(
                        modifier = modifier
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