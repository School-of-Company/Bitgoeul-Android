package com.bitgoeul.inquiry

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.NegativeBitgoeulButton
import com.msg.design_system.component.dialog.NegativeActionDialog
import com.msg.design_system.component.dialog.PositiveActionDialog

@Composable
fun InquiryDetailRoute() {
    InquiryDetailScreen()
}

@Composable
fun InquiryDetailScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {

    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp)
                .verticalScroll(scrollState),
        ) {
            Spacer(modifier = modifier.height(20.dp))

            GoBackTopBar(
                icon = { GoBackIcon() },
                text = stringResource(id = R.string.go_back)
            ) {

            }

            Spacer(modifier = modifier.height(24.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "답변 대기 중",
                    style = typography.labelMedium,
                    color = colors.E5
                )

                Spacer(modifier = modifier.weight(1f))

                Text(
                    text = "2023 11월 11일 12:34에 수정됨",
                    style = typography.labelMedium,
                    color = colors.G1
                )
            }

            Spacer(modifier = modifier.height(4.dp))

            Text(
                text = "국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다.",
                style = typography.bodyLarge,
                color = colors.BLACK
            )

            Spacer(modifier = modifier.height(4.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "2023.10.31 활동",
                    style = typography.bodySmall,
                    color = colors.G2
                )

                Spacer(modifier = modifier.weight(1f))

                Text(
                    text = "게시자 홍길동",
                    style = typography.bodySmall,
                    color = colors.G1
                )
            }

            Spacer(modifier = modifier.height(24.dp))

            Text(
                text = "문의 사항 임시 문자열입니다. 문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.문의 사항 임시 문자열입니다.",
                style = typography.headlineSmall,
                color = colors.BLACK
            )

            NegativeActionDialog(
                title = stringResource(id = R.string.delete_inquiry_dialog_title),
                negativeAction = stringResource(id = R.string.delete),
                content = "임시",
                isVisible = false, // 임시
                onQuit = { /*TODO*/ }) {
                
            }
            
            PositiveActionDialog(
                title = stringResource(id = R.string.answer_inquiry_dialog_title),
                positiveAction = stringResource(id = R.string.add),
                content = "임시",
                isVisible = false, // 임시
                onQuit = { /*TODO*/ }) {
                
            }
        }

        // 추후 Role 처리
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    NegativeBitgoeulButton(
                        modifier = modifier.weight(1f),
                        text = stringResource(id = R.string.delete_inquiry)
                    ) {

                    }

                    Spacer(modifier = modifier.width(8.dp))

                    BitgoeulButton(
                        modifier = modifier.weight(1f),
                        text = stringResource(id = R.string.answer_inquiry)
                    ) {

                    }
                }
            }

            Spacer(modifier = modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun InquiryDetailScreenPre() {
    InquiryDetailScreen()
}