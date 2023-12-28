package com.msg.main.banner.industry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.msg.main.component.AutoIndustryGridView
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun EnergyIndustryBanner(
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .height(720.dp)
                .fillMaxWidth()
                .background(color = colors.BLACK)
        ) {
            AsyncImage(
                modifier = modifier.blur(15.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.mipmap.bg_energy)
                    .build(),
                contentDescription = "Blurred Image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(96.dp))
                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "에너지산업",
                    style = typography.titleMedium,
                    color = colors.WHITE
                )
                AutoIndustryGridView(
                    rowItems = listOf(
                        "(유)삼환",
                        "(주)휘라포토닉스",
                        "(주)스위코진광",
                        "LG이노텍(주)",
                        "(주)유진테크노",
                        "(주)LCM에너지솔루션",
                        "주식회사 금철",
                        "주식회사 칼선",
                        "(주)남양에스티엔",
                        "주식회사 유니컴퍼니",
                        "제이제이파트너스(주)"
                    )
                )
            }
        }
    }
}