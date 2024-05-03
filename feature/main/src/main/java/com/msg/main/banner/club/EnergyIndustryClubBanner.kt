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
fun EnergyIndustryClubBanner(
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
                AutoSchoolClubGridView(
                    school = "광주공업고등학교",
                    rowItems = listOf("전기가 미래다", "전자 어벤져", "전자 히어로스")
                )
                AutoSchoolClubGridView(
                    school = "광주자동화설비마이스터고등학교",
                    rowItems = listOf("HMI 동아리", "마취제", "빛go job go", "취업진로 동아리")
                )
                AutoSchoolClubGridView(
                    school = "광주전자공업고등학교",
                    rowItems = listOf("ACT", "ECT", "Tesla")
                )
                AutoSchoolClubGridView(
                    school = "금파공업고등학교",
                    rowItems = listOf("블라썸(blossom)", "유선통신", "전기꿈나무")
                )
                AutoSchoolClubGridView(
                    school = "동일미래과학고등학교",
                    rowItems = listOf("믿고잡고")
                )
                AutoSchoolClubGridView(
                    school = "숭의과학기술고등학교",
                    rowItems = listOf("드림온", "볼트와암페어")
                )
                AutoSchoolClubGridView(
                    school = "전남공업고등학교",
                    rowItems = listOf("에너지지키미")
                )
            }
        }
    }
}