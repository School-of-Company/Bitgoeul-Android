package com.msg.main.banner.club

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
import com.msg.main.component.AutoSchoolClubGridView

@Composable
fun AiFusionAndIntegrationClubBanner(
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .height(1200.dp)
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
                AutoSchoolClubGridView(
                    school = "광주공업고등학교",
                    rowItems = listOf("Civil 마스터")
                )
                AutoSchoolClubGridView(
                    school = "광주소프트웨어마이스터고등학교",
                    rowItems = listOf("dev_GSM")
                )
                AutoSchoolClubGridView(
                    school = "금파공업고등학교",
                    rowItems = listOf("다이나믹(Dynamic)")
                )
                AutoSchoolClubGridView(
                    school = "숭의과학기술학교",
                    rowItems = listOf("비상", "캐치어드론")
                )
                AutoSchoolClubGridView(
                    school = "전남공업고등학교",
                    rowItems = listOf("스카이드론")
                )
            }
        }
    }
}