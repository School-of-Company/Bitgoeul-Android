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
fun AiFusionAndIntegrationBanner(
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
                    .data(R.mipmap.bg_ai)
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
                    text = "AI 융•복합",
                    style = typography.titleMedium,
                    color = colors.WHITE
                )
                Spacer(modifier = modifier.height(24.dp))
                AutoIndustryGridView(
                    rowItems = listOf(
                        "(주)서치",
                        "동아간호학원",
                        "네일온",
                        "더이인나라",
                        "뉴디헤어",
                        "특수전사령부",
                        "해병대"
                    )
                )
            }
        }
    }
}