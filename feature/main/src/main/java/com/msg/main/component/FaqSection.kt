package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse

@Composable
fun FaqSection(
    modifier: Modifier = Modifier,
    data: List<GetFrequentlyAskedQuestionDetailResponse>
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp)
        ) {
            Text(
                text = "FAQ",
                style = typography.labelMedium,
                color = colors.G1
            )
            Text(
                text = "자주묻는 질문",
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(24.dp))
            data.forEach {
                FaqItem(data = it)
                Spacer(modifier = modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun FaqSectionPre() {
    FaqSection(
        data = listOf(
            GetFrequentlyAskedQuestionDetailResponse(
                id = 0,
                question = "학원에서 자격증 과정을 운영할 수 있나요?",
                answer = "불가능 합니다. 그러나, 학교 주관으로 학원강사를 섭외할 수는 있고, 학원시설 이용비, 학원강사 수당 지급은 가능 합니다."
            )
        )
    )
}