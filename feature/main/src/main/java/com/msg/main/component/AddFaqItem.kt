package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun AddFaqItem(
    modifier: Modifier = Modifier,
    questionValue: String,
    onQuestionValueChanged: (String) -> Unit,
    answerValue: String,
    onAnswerValueChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    val isSelected = remember { mutableStateOf(false) }
    val questionHintVisible = remember {
        derivedStateOf { questionValue.isEmpty() }
    }
    val answerHintVisible = remember {
        derivedStateOf { answerValue.isEmpty() }
    }
    val interactionSource = remember { MutableInteractionSource() }

    BitgoeulAndroidTheme { colors, typography ->
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = colors.WHITE
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .background(
                    color = colors.WHITE,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Spacer(modifier = modifier.height(16.dp))
            if (!isSelected.value) {
                Text(
                    modifier = modifier
                        .clickable(
                            indication = null,
                            interactionSource = interactionSource
                        ) {
                            isSelected.value = true
                        }
                        .padding(horizontal = 20.dp),
                    text = "+ 자주 묻는 질문 추가하기",
                    style = typography.bodySmall,
                    color = colors.P5
                )
            } else {
                BasicTextField(
                    modifier = modifier.padding(horizontal = 20.dp),
                    onValueChange = onQuestionValueChanged,
                    value = questionValue,
                    decorationBox = { innerTextField ->
                        Row {
                            Text(
                                text = "Q.",
                                style = typography.bodySmall,
                                color = colors.P5
                            )
                            if (questionHintVisible.value) Text(
                                text = "질문 작성하기",
                                style = typography.bodySmall,
                                color = colors.G1
                            )
                            Spacer(modifier = modifier.width(4.dp))
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = modifier.height(12.dp))
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp),
                    thickness = 1.dp,
                    color = colors.G2
                )
                Spacer(modifier = modifier.height(12.dp))
                BasicTextField(
                    modifier = modifier.padding(horizontal = 20.dp),
                    onValueChange = onAnswerValueChanged,
                    value = answerValue,
                    decorationBox = { innerTextField ->
                        Row {
                            Text(
                                text = "A. ",
                                style = typography.bodySmall,
                                color = colors.P5
                            )
                            if (answerHintVisible.value) Text(
                                text = "답변 작성하기",
                                style = typography.bodySmall,
                                color = colors.G1
                            )
                            Spacer(modifier = modifier.width(4.dp))
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = modifier.height(12.dp))
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp),
                    thickness = 1.dp,
                    color = colors.G2
                )
                Spacer(modifier = modifier.height(12.dp))
                Row(
                    modifier = modifier.padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        modifier = modifier.clickable(
                            indication = null,
                            interactionSource = interactionSource
                        ) {
                            isSelected.value = false
                        },
                        text = "취소",
                        style = typography.bodySmall,
                        fontWeight = FontWeight(600),
                        color = colors.G1
                    )
                    Spacer(modifier = modifier.width(24.dp))
                    Text(
                        modifier = modifier.clickable(
                            indication = null,
                            interactionSource = interactionSource
                        ) {
                            onAddClicked()
                            isSelected.value = false
                        },
                        text = "작성",
                        style = typography.bodySmall,
                        fontWeight = FontWeight(600),
                        color = colors.P5
                    )
                }
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun AddFaqItemPre() {
    AddFaqItem(
        questionValue = "",
        onQuestionValueChanged = {},
        answerValue = "",
        onAnswerValueChanged = {},
        onAddClicked = {}
    )
}
