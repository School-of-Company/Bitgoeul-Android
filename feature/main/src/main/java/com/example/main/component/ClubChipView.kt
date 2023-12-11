package com.example.main.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun ClubChipView(
    modifier: Modifier = Modifier,
    clubName: String
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = colors.WHITE,
                    shape = RoundedCornerShape(size = 999.dp)
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = clubName,
            style = typography.bodySmall,
            color = colors.WHITE
        )
    }

}

@Preview
@Composable
fun ClubChipViewPre() {
    ClubChipView(clubName = "SMART JOB PROJECT")
}