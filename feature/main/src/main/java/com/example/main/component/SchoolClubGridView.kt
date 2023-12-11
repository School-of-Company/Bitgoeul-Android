package com.example.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun SchoolClubGridView(
    modifier: Modifier = Modifier,
    school: String,
    rows: Int,
    rowItems: List<List<String>>,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = school,
                style = typography.bodyLarge,
                color = colors.WHITE
            )
            Spacer(modifier = modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (i in 0 until rows) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems[i].forEach {
                            ClubChipView(clubName = it)
                        }
                    }
                }
            }
        }
        
    }
}
