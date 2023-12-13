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
import com.msg.main.component.AutoSchoolClubGridView
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R

@Composable
fun CultureIndustryClubBanner(
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Surface(
            modifier = modifier
                .height(1200.dp)
                .fillMaxWidth()
                .background(colors.BLACK)
        ) {
            AsyncImage(
                modifier = modifier.blur(
                    radius = 15.dp
                ),
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
                AutoSchoolClubGridView(
                    school = "광주공업고등학교",
                    rowItems = listOf("건축연구소")
                )
                AutoSchoolClubGridView(
                    school = "광주여자상업고등학교",
                    rowItems = listOf("금융실무", "소개팅", "취사모")
                )
                AutoSchoolClubGridView(
                    school = "광주자연과학고등학교",
                    rowItems = listOf("DCT", "뉴 쿡", "우아행", "베이커리 카페 CEO")
                )
                AutoSchoolClubGridView(
                    school = "광주전자공업고등학교",
                    rowItems = listOf("\"M lab\" = 메이커 연구소")
                )
                AutoSchoolClubGridView(
                    school = "금파공업고등학교",
                    rowItems = listOf("금호로80 베이커리")
                )
                AutoSchoolClubGridView(
                    school = "송원여자상업고등학교",
                    rowItems = listOf("클로즈업")
                )
                AutoSchoolClubGridView(
                    school = "숭의과학기술고등학교",
                    rowItems = listOf("내빵네빵", "카페바리", "쿠킹마스터즈")
                )
                AutoSchoolClubGridView(
                    school = "전남공업고등학교",
                    rowItems = listOf("그린라이트")
                )
            }
        }
    }
}