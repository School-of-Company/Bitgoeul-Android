package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun TagChipView(
    modifier: Modifier = Modifier,
    tagText: String
) {
    BitgoeulAndroidTheme { colors, typography ->  
       Text(
           modifier = modifier
               .background(
                   color = colors.P3,
                   shape = RoundedCornerShape(size = 999.dp)
               )
               .padding(horizontal = 12.dp, vertical = 4.dp),
           text = "#$tagText",
           style = typography.bodySmall,
           color = colors.WHITE
       )
    }
}

@Preview
@Composable
fun TagChipVewPre() {
    TagChipView(tagText = "교육과정_운영")
}