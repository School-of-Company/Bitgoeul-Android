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
fun MedicalHealthBanner(
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
                    .data(R.mipmap.bg_medical_health)
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
                    text = "의료•헬스케어",
                    style = typography.titleMedium,
                    color = colors.WHITE
                )
                AutoIndustryGridView(
                    rowItems = listOf(
                        "엠코테크놀로지코리아(주)",
                        "(주)여보야",
                        "인디제이",
                        "지니소프트",
                        "건주이엔씨",
                        "연제측량기술원",
                        "드론캐스트"
                    )
                )
            }
        }
    }
}