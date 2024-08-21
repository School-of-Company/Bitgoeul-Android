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
import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.enumdata.Field

@Composable
internal fun GovernmentBannerItem(
    modifier: Modifier = Modifier,
    data: List<String>,
    field: Field
) {
    BitgoeulAndroidTheme { colors, typography ->
        val uiData: UiData = when (field) {
            Field.MEDICAL_HEALTHCARE -> {
                UiData(
                    color = colors.P3,
                    field = "의료\n헬스케어",
                    logo = null,
                    governmentList = data
                )
            }
            Field.AI_CONVERGENCE -> {
                UiData(
                    color = colors.P2,
                    field = "AI\n융복합",
                    logo = null,
                    governmentList = data
                )
            }
            Field.CULTURE -> {
                UiData(
                    color = colors.P1,
                    field = "문화\n산업",
                    logo = null,
                    governmentList = data
                )
            }
            Field.ENERGY -> {
                UiData(
                    color = colors.P4,
                    field = "에너지\n산업",
                    logo = null,
                    governmentList = data
                )
            }
            Field.FUTURISTIC_TRANSPORTATION_EQUIPMENT -> {
                UiData(
                    color = colors.P5,
                    field = "미래형\n운송기기",
                    logo = null,
                    governmentList = data
                )
            }
            else -> {
                UiData(
                    color = colors.G1,
                    field = "error\ntext",
                    logo = {},
                    governmentList = data
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
    GovernmentBannerItem(
        field = Field.MEDICAL_HEALTHCARE,
        data = listOf("한국평생교육연합회")
    )
}