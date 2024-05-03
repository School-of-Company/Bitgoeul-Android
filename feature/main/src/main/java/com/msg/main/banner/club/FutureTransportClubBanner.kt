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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.main.component.AutoSchoolClubGridView
import com.msg.ui.DevicePreviews

@Composable
fun FutureTransportClubBanner(
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
                modifier = modifier.blur(
                    radius = 15.dp,
                ),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.mipmap.bg_future_transport)
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
                    text = "미래형 운송기기",
                    style = typography.titleMedium,
                    color = colors.WHITE
                )
                AutoSchoolClubGridView(
                    school = "광주공업고등학교",
                    rowItems = listOf(
                        "SMART JOB PROJECT",
                        "나의 미래는 내가 주인공이다!",
                        "설비의 달인",
                        "특수용접 화이팅!"
                    )
                )
                AutoSchoolClubGridView(
                    school = "광주전자공업고등학교",
                    rowItems = listOf("감성기계", "열정 그 자체")
                )
                AutoSchoolClubGridView(
                    school = "금파공업고등학교",
                    rowItems = listOf("레프리")
                )
                AutoSchoolClubGridView(
                    school = "동일미래과학고등학교",
                    rowItems = listOf("놀GO잡GO")
                )
                AutoSchoolClubGridView(
                    school = "숭의과학기술고등학교",
                    rowItems = listOf("서전트스나이퍼", "카-페인팅")
                )
                AutoSchoolClubGridView(
                    school = "전남공업고등학교",
                    rowItems = listOf("진짜기계", "핫앤쿨")
                )
            }
        }
    }
}



@Preview(name = "bannertest", device = "spec:width=360dp,height=1200dp,dpi=420")
@DevicePreviews
@Composable
fun FutureTransportBannerPre() {
    FutureTransportClubBanner()
}