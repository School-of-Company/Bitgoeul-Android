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
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.main.component.AutoIndustryGridView

@Composable
fun CultureIndustryBanner(
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
                    .data(R.mipmap.bg_culture_industry)
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
                    text = "문화산업",
                    style = typography.titleMedium,
                    color = colors.WHITE
                )
                Spacer(modifier = modifier.height(24.dp))
                AutoIndustryGridView(
                    rowItems = listOf(
                        "(주)광주은행",
                        "파란손해사정(주)",
                        "한국조경수협회",
                        "(사)일도시연구소",
                        "24노아동물메디컬센터",
                        "광주동물메디컬센터",
                        "(주)브레드세븐",
                        "파파레브",
                        "소맥베이커리",
                        "스튜디오버턴",
                        "공감플리서",
                        "(주)195F&B",
                        "가매",
                        "파운데이"
                    )
                )
            }
        }
    }
}