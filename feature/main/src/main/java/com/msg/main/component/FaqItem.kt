package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.theme.pretendard
import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity

@Composable
internal fun FaqItem(
    modifier: Modifier = Modifier,
    data: GetFrequentlyAskedQuestionDetailEntity
) {
    val isSelected = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, _ ->
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = colors.WHITE
            ),
            modifier = modifier
                .background(
                    color = colors.WHITE,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    isSelected.value = !isSelected.value
                }
        ) {
            Spacer(modifier = modifier.height(16.dp))
            Text(
                modifier = modifier.padding(horizontal = 20.dp),
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = colors.P5,
                            fontFamily = pretendard,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                        )
                    ) {
                        append("Q. ")
                    }
                    withStyle(
                        SpanStyle(
                            color = colors.BLACK,
                            fontFamily = pretendard,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400)
                        )
                    ) {
                        append(data.question)
                    }
                }
            )
            if (isSelected.value) {
                Spacer(modifier = modifier.height(12.dp))
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 13.dp),
                    thickness = 1.dp,
                    color = colors.G9
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    modifier = modifier.padding(horizontal = 20.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = colors.P5,
                                fontFamily = pretendard,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                            )
                        ) {
                            append("A. ")
                        }
                        withStyle(
                            SpanStyle(
                                color = colors.G2,
                                fontFamily = pretendard,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                        ) {
                            append(data.answer)
                        }
                    }
                )
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun FaqItemPre() {
    FaqItem(
        data = GetFrequentlyAskedQuestionDetailEntity(
            id = 0,
            question = "학원에서 자격증 과정을 운영할 수 있나요?",
            answer = "불가능 합니다. 그러나, 학교 주관으로 학원강사를 섭외할 수는 있고, 학원시설 이용비, 학원강사 수당 지급은 가능 합니다."
        )
    )
}