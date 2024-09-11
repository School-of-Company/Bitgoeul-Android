package com.bitgoeul.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.tag.NegativeTag
import com.msg.design_system.R
import com.msg.design_system.component.tag.PositiveTag
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun InquiryCard(
    modifier: Modifier = Modifier,
    role: String,
    answerStatus: Boolean,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = modifier
                .wrapContentSize()
                .background(color = colors.WHITE, shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = colors.G1, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = colors.WHITE),
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "문의 사항 임시 문자열",
                style = typography.bodyLarge,
                color = colors.BLACK,
            )

            Text(
                modifier = modifier.fillMaxWidth(),
                text = "2023년 11월 11일",
                style = typography.bodySmall,
                color = colors.G1,
            )

            Text(
                modifier = modifier.fillMaxWidth(),
                text = "홍길동",
                style = typography.bodySmall,
                color = colors.G1,
            )

            Spacer(modifier = modifier.height(24.dp))

            if (role == "ROLE_ADMIN") {
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = modifier.weight(1f))

                    if (answerStatus) {
                        PositiveTag(
                            text = stringResource(id = R.string.answer_complete)
                        )
                    }

                    if (answerStatus) {
                        NegativeTag(
                            text = stringResource(id = R.string.waiting_for_answer)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun InquiryCardPre() {
    InquiryCard(
        role = "ROLE_ADMIN",
        answerStatus = true
    )
}