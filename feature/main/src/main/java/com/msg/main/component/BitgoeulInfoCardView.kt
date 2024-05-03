package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun BitgoeulInfoCardView(
    modifier: Modifier,
    title: String,
    contentList: List<String>
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colors.WHITE),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            )
        ) {
            Box(modifier = modifier
                .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = title,
                        style = typography.titleSmall,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    contentList.forEach {
                        Text(
                            text = "    • $it",
                            style = typography.bodyMedium,
                            color = colors.G2
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BitgoeulInfoCardViewPre() {
    BitgoeulInfoCardView(
        modifier = Modifier,
        title = "\uD83C\uDFEB 직업계고",
        contentList = listOf("교육과정 운영", "진로 지도", "학생 관리")
    )
}