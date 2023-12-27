package com.msg.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.AiIcon
import com.msg.design_system.component.icon.EnergyIcon
import com.msg.design_system.component.icon.FutureTransportIcon
import com.msg.design_system.component.icon.MedicalIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun GovernmentBannerItem(
    modifier: Modifier = Modifier,
    field: String
) {
    BitgoeulAndroidTheme { colors, typography ->
        val uiData: UiData = when (field) {
            "Medical" -> {
                UiData(
                    color = colors.P3,
                    field = "의료\n헬스케어",
                    logo = { MedicalIcon() },
                    governmentList = listOf("(사)한국평생교육연합회")
                )
            }
            "AI" -> {
                UiData(
                    color = colors.P2,
                    field = "AI\n융복합",
                    logo = { AiIcon() },
                    governmentList = listOf("(사)스마트인재개발원")
                )
            }
            "Culture" -> {
                UiData(
                    color = colors.P1,
                    field = "문화\n산업",
                    logo = null,
                    governmentList = listOf("시청자미디어재단", "(재)광주정보문화산업진흥원", "광주광역시청소년삶디자인센터")
                )
            }
            "Energy" -> {
                UiData(
                    color = colors.P4,
                    field = "에너지\n산업",
                    logo = { EnergyIcon() },
                    governmentList = listOf("에너지밸리기업개발원")
                )
            }
            "FutureTransport" -> {
                UiData(
                    color = colors.P5,
                    field = "미래형\n운송기기",
                    logo = { FutureTransportIcon() },
                    governmentList = listOf("(재)광주그린카진흥원")
                )
            }
            else -> {
                UiData(
                    color = colors.G1,
                    field = "error\ntext",
                    logo = {},
                    governmentList = listOf("error")
                )
            }
        }
        Column(
            modifier = modifier
                .width(200.dp)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier
                    .drawBehind {
                        drawCircle(
                            color = uiData.color
                        )
                    }
                    .padding(vertical = 24.dp, horizontal = 28.dp),
                text = uiData.field,
                style = typography.bodyLarge,
                color = colors.WHITE,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(16.dp))
            uiData.logo?.run {
                invoke()
                Spacer(modifier = modifier.height(8.dp))
            }
            uiData.governmentList.forEach {
                Text(
                    text = it,
                    style = typography.bodySmall,
                    color = colors.BLACK
                )
            }
        }
    }
}

data class UiData(
    val color: Color,
    val field: String,
    val logo: @Composable (() -> Unit)?,
    val governmentList: List<String>
)

@Preview
@Composable
fun GovernmentBannerItemPre() {
    GovernmentBannerItem(field = "Medical")
}