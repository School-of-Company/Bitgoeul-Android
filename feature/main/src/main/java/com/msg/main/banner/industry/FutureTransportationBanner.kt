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
fun FutureTransportationBanner(
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
                Spacer(modifier = modifier.height(24.dp))
                AutoIndustryGridView(
                    rowItems = listOf(
                        "보람엔지니어링",
                        "(주)인탑스테크닉",
                        "(주)삼도환경",
                        "에이테크솔루션(주)",
                        "창원종합사격장",
                        "제3함대(해군)",
                        "동양통상",
                        "다이나믹 디자인"
                    )
                )
            }
        }
    }
}