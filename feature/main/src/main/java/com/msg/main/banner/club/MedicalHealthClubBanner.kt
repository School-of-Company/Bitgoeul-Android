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
internal fun MedicalHealthClubBanner(
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
                AutoSchoolClubGridView(
                    school = "광주전자공업고등학교",
                    rowItems = listOf("발자국")
                )
                AutoSchoolClubGridView(
                    school = "금파공업고등학교",
                    rowItems = listOf("어썸(awesome)")
                )
                AutoSchoolClubGridView(
                    school = "동일미래과학고등학교",
                    rowItems = listOf("따고잡고", "쓰고잡고", "하고잡고")
                )
                AutoSchoolClubGridView(
                    school = "송원여자상업고등학교",
                    rowItems = listOf("건강지킴이", "미용서비스", "뷰티아트")
                )
                AutoSchoolClubGridView(
                    school = "숭의과학기술고등학교",
                    rowItems = listOf("크로스핏마스터")
                )
                AutoSchoolClubGridView(
                    school = "전남공업고",
                    rowItems = listOf("라온하제")
                )
            }
        }
    }
}