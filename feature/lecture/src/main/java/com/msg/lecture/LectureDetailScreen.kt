package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.dialog.LectureApplicationDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.dialog.NegativeActionDialog

@Composable
fun LectureDetailScreen(
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var isDialogVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(colors.WHITE),
        ) {
            Column(
                modifier = Modifier
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClick = { onBackClick() }
                )

                LectureDetailContent()

            }

            BitgoeulButton(
                text = "수강 신청하기",
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp),
            ) {
                isDialogVisible.value = !isDialogVisible.value
            }

            LectureApplicationDialog(
                content = "님;나이;ㅁ나ㅣ;ㅇ민;아ㅣ;ㅁ나ㅣ;임니;", // 임의로 정한것임 추후 Detail 조회시 넘어오는 Content Text 값으로 로직 추가 예정
                onQuit = { isDialogVisible.value = false },
                isVisible = isDialogVisible.value,
            )
        }
    }
}

@Composable
fun LectureDetailContent() {
    BitgoeulAndroidTheme { colors, type ->
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(horizontal = 24.dp)

        ) {

            Text(
                modifier = Modifier
                    .width(133.dp)
                    .height(20.dp),
                text = "#" + stringResource(id = R.string.mutual_credit_recognition_curriculum), // 서버 리스폰스로 변경 예정
                color = colors.P3,
                style = type.labelMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "국가는 국민의 국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다.", // 서버 리스폰스로 변경 예정
                color = colors.BLACK,
                style = type.bodyLarge,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    modifier = Modifier
                        .width(239.dp)
                        .height(22.dp),
                    text = "2023.11.19 ~ 2023.11.25", // 서버 리스폰스로 변경 예정
                    color = colors.P5,
                    style = type.bodySmall
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(22.dp),
                    textAlign = TextAlign.End,
                    text = "55/100명",
                    color = colors.P5,
                    style = type.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.wrapContentSize()
            ) {

                Text(
                    modifier = Modifier
                        .width(225.dp)
                        .height(20.dp),
                    text = "2023.12.25 부터 시작",  // 서버 리스폰스로 변경 예정
                    color = colors.G2,
                    style = type.labelMedium
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    textAlign = TextAlign.End,
                    text = "학점 2점 부여", // 서버 리스폰스로 변경 예정
                    color = colors.G2,
                    style = type.labelMedium
                )
            }

            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    modifier = Modifier
                        .width(214.dp)
                        .height(20.dp), text = "2023.11.23 에 게시",
                    color = colors.G1,
                    style = type.labelMedium
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    textAlign = TextAlign.End,
                    text = "박주홍 교수님",
                    color = colors.G1,
                    style = type.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                text = "강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수 강수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수강민수 강민수 강민수 강민수 강민수 강민수 강민수 강민수", // 서버 리스폰스로 변경 예정
                color = colors.BLACK,
                style = type.bodySmall
            )
        }
    }
}

@Composable
fun ApplicationRequestButton(
    modifier: Modifier = Modifier,
) {
    val isDialogVisible = remember { mutableStateOf(false) }
    BitgoeulAndroidTheme { colors, type ->
        Row {
            Box(
                modifier = modifier
                    .background(
                        color = colors.E5,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 32.dp)
                    .clickable {
                        isDialogVisible.value = !isDialogVisible.value
                    },
            ) {
                Text(
                    text = stringResource(id = R.string.application_reject),
                    color = colors.WHITE,
                    style = type.bodyLarge
                )

                NegativeActionDialog(
                    title = "신청 거부하시겠습니까?",
                    negativeAction = "거부",
                    isVisible = isDialogVisible.value,
                    content = "askdl;asd",
                    onQuit = {
                        isDialogVisible.value = !isDialogVisible.value
                    },
                    onActionClicked = {}
                )
            }

            Spacer(modifier = modifier.width(8.dp))
            Box(
                modifier = modifier
                    .background(
                        color = colors.P5,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 32.dp)
                    .clickable {
                        isDialogVisible.value = !isDialogVisible.value
                    },
            ) {
                Text(
                    text = stringResource(id = R.string.application_approve),
                    color = colors.WHITE,
                    style = type.bodyLarge
                )

                PositiveActionDialog(
                    title = "신청 승인하시겠습니까?",
                    isVisible = isDialogVisible.value,
                    content = "askdl;asd",
                    positiveAction = "승신",
                    onQuit = {
                        isDialogVisible.value = !isDialogVisible.value
                    },
                    onActionClicked = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureDetailScreenPre() {
    LectureDetailScreen(onBackClick = {})
}