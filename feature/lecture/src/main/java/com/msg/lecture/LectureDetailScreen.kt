package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureDetailScreen(
    onBackClick: () -> Unit,
) {
    BitgoeulAndroidTheme { colors, type ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = Modifier.background(color = colors.WHITE)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClick = { onBackClick() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .width(133.dp)
                            .height(20.dp),
                        text = "#" + stringResource(id = com.msg.design_system.R.string.mutual_credit_recognition_curriculum), // 서버 리스폰스로 변경 예정
                        color = colors.P3,
                        style = type.labelMedium,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        modifier = Modifier
                            .width(304.dp)
                            .height(130.dp),
                        text = "국가는 국민의 국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다.",
                        color = colors.BLACK,
                        style = type.bodyLarge,
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun LectureDetailScreenPre() {
    LectureDetailScreen(onBackClick = {})
}