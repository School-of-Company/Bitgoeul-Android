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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
internal fun BitgoeulInfoCardView(
    modifier: Modifier,
    title: String,
    icon: String,
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
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = icon,
                        style = typography.titleLarge,
                        color = colors.BLACK,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = title,
                        style = typography.titleSmall,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    AutoTagGridView(rowItems = contentList)
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
        title = "직업계고",
        icon = "\uD83C\uDFEB ",
        contentList = listOf("교육과정 운영", "진로 지도", "학생 관리")
    )
}