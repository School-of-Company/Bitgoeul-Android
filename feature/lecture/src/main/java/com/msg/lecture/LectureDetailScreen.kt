package com.msg.lecture

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.dialog.LectureApplicationDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.ui.util.toKoreanFormat
import com.msg.ui.util.toLocalTimeFormat

@Composable
fun LectureDetailRoute(
    onBackClick: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel()
) {
    val role = remember { mutableStateOf(Authority.ROLE_USER) }
    val id = viewModel.selectedLectureId.value
    LaunchedEffect(true) {
        role.value = viewModel.getRole()
        viewModel.getDetailLecture(id = id)
    getLectureDetailData(
            viewModel = viewModel,
            onSuccess = { lectureDetailData ->
                viewModel.lectureDetailData.value = lectureDetailData
            }
        )
    }
    LectureDetailScreen(
        onBackClick = onBackClick,
        data = viewModel.lectureDetailData.value,
    )
}

suspend fun getLectureDetailData(
    viewModel: LectureViewModel,
    onSuccess: (data: DetailLectureResponse) -> Unit
) {
    viewModel.getDetailLectureResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
fun LectureDetailScreen(
    data: DetailLectureResponse,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var isDialogVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.WHITE),
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
            ) {

                Spacer(modifier = modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClick = { onBackClick() }
                )

                Spacer(modifier = modifier.height(24.dp))
                Text(
                    text = "# " + "${data.lectureType}",
                    color = colors.P3,
                    style = typography.labelMedium,
                )

                Spacer(modifier = modifier.height(4.dp))

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when (data.division) {
                            Division.AUTOMOBILE_INDUSTRY -> "자동차 산업"
                            Division.ENERGY_INDUSTRY -> "에너지 산업"
                            Division.MEDICAL_HEALTHCARE -> "의료헬스케어"
                            Division.AI_CONVERGENCE_AI -> "AI 융복합"
                            Division.CULTURAL_INDUSTRY -> "문화 산업"
                        },
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    Spacer(
                        modifier = modifier
                            .size(1.dp, 14.dp)
                            .background(color = colors.G1)
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    Text(
                        text = "${data.line}",
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    Spacer(
                        modifier = modifier
                            .size(1.dp, 14.dp)
                            .background(color = colors.G1)
                    )

                    Spacer(modifier = modifier.width(8.dp))


                    Text(
                        text = "${data.department}" + " 학과",
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                }

                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = "${data.name}",
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                Spacer(modifier = modifier.height(4.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "2023.12.25 부터 시작",  // 서버 리스폰스로 변경 예정
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                    Text(
                        text = "학점 ${data.credit}점 부여", // 서버 리스폰스로 변경 예정
                        color = colors.G2,
                        style = typography.labelMedium
                    )
                }

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "2023.11.23 에 게시",
                        color = colors.G1,
                        style = typography.labelMedium
                    )

                    Text(
                        text = "${data.lecturer} 교수님",
                        color = colors.G1,
                        style = typography.labelMedium
                    )
                }

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    modifier = modifier.padding(bottom = 24.dp),
                    text = "${data.content}",
                    color = colors.BLACK,
                    style = typography.bodySmall
                )

                Spacer(
                    modifier = modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = colors.G9)
                )

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = "수강 신청 기간",
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = "• $data",
                    color = colors.G2,
                    style = typography.bodySmall
                )

                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = "• ${data.lectureDates}",
                    color = colors.G2,
                    style = typography.bodySmall
                )

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = "강의 수강 날짜",
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                // TODO: 서버에서 온 LectureDates List형식 사이즈만큼 돌리기
                Spacer(modifier = modifier.height(16.dp))
                data.lectureDates.forEach { lectureDate ->
                    Text(
                        text = "• ${lectureDate.completeDate.toKoreanFormat()}" + "${lectureDate.startTime.toLocalTimeFormat()} ~ " + "${lectureDate.endTime.toLocalTimeFormat()}",
                        color = colors.G2,
                        style = typography.bodySmall
                    )
                }

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = "모집 정원",
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = "100명",
                    color = colors.G2,
                    style = typography.bodySmall
                )
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        BitgoeulButton(
                            text = "수강 신청하기",
                            modifier = modifier
                                .padding(bottom = 40.dp)
                                .fillMaxWidth()
                                .height(52.dp)
                                .align(Alignment.BottomCenter)
                                .padding(horizontal = 24.dp),
                        ) {
                            isDialogVisible.value = !isDialogVisible.value
                        }
                    }
                }
            }

            LectureApplicationDialog(
                content = "님;나이;ㅁ나ㅣ;ㅇ민;아ㅣ;ㅁ나ㅣ;임니;", // 임의로 정한것임 추후 Detail 조회시 넘어오는 Content Text 값으로 로직 추가 예정
                onQuit = { isDialogVisible.value = false },
                isVisible = isDialogVisible.value,
            )
        }
    }
}