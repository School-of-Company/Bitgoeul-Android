package com.msg.lecture

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.ApplicationDoneButton
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.dialog.NegativeActionDialog
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.icon.KebabIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureExcelDownloadBottomSheet
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.ui.util.toKoreanFormat
import com.msg.ui.util.toLocalTimeFormat

@Composable
fun LectureDetailRoute(
    onBackClicked: () -> Unit,
    onLectureTakingStudentListScreenClick: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
) {
    val id = viewModel.selectedLectureId.value
    val role = remember { mutableStateOf(Authority.ROLE_USER) }

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
        role = role.value,
        data = viewModel.lectureDetailData.value,
        onBackClicked = onBackClicked,
        onLectureTakingStudentListScreenClick = onLectureTakingStudentListScreenClick,
        onApplicationCancelClick = {
            viewModel.lectureApplicationCancel(viewModel.selectedLectureId.value)
        },
        onApplicationClick = {
            viewModel.lectureApplication(viewModel.selectedLectureId.value)
        },
        onDownloadButtonClick = {
            viewModel.downloadLectureExcel()
        }
    )
}

suspend fun getLectureDetailData(
    viewModel: LectureViewModel,
    onSuccess: (data: DetailLectureResponse) -> Unit,
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
    role: Authority,
    data: DetailLectureResponse,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onApplicationClick: () -> Unit,
    onApplicationCancelClick: () -> Unit,
    onLectureTakingStudentListScreenClick: () -> Unit,
    onDownloadButtonClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val isExcelDownloadSheetVisible = remember { mutableStateOf(false) }
    val isPositiveActionDialogVisible = remember { mutableStateOf(false) }
    val isNegativeDialogVisible = remember { mutableStateOf(false) }
    val isApplicationState = remember { mutableStateOf(false) }

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
                Row {
                    GoBackTopBar(
                        icon = { GoBackIcon() },
                        text = "돌아가기",
                        onClick = { onBackClicked() }
                    )

                    Spacer(modifier = modifier.weight(1f))

                    KebabIcon(
                        modifier.clickable {
                            isExcelDownloadSheetVisible.value = true
                        }
                    )
                }

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = "# " + data.lectureType,
                    color = colors.P3,
                    style = typography.labelMedium,
                )

                Spacer(modifier = modifier.height(4.dp))

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.division,
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
                        text = data.line,
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
                        text = data.department + " 학과",
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                }

                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = data.name,
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                Spacer(modifier = modifier.height(4.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = data.semester,
                        color = colors.G2,
                        style = typography.labelMedium
                    )

                    Text(
                        text = "학점 ${data.credit}점 부여",
                        color = colors.G2,
                        style = typography.labelMedium
                    )
                }

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${data.createAt.toKoreanFormat()} 에 게시",
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
                    text = data.content,
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
                    text = "• ${data.startDate.toKoreanFormat()}",
                    color = colors.G2,
                    style = typography.bodySmall
                )

                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = "• ${data.endDate.toKoreanFormat()}",
                    color = colors.G2,
                    style = typography.bodySmall
                )

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = "강의 수강 날짜",
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

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
                    text = "${data.maxRegisteredUser}명",
                    color = colors.G2,
                    style = typography.bodySmall
                )
            }
            when(role) {
                Authority.ROLE_ADMIN -> {
                    BitgoeulButton(
                        text = "수강 명단 확인",
                        modifier = modifier
                            .padding(bottom = 40.dp)
                            .fillMaxWidth()
                            .height(52.dp)
                            .align(Alignment.BottomCenter),
                        state = ButtonState.Enable
                    ) {
                        onLectureTakingStudentListScreenClick()
                    }
                }
                else -> {}
            }

            when (data.lectureStatus) {
                LectureStatus.OPEN -> {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            when (isApplicationState.value) {
                                true -> {
                                    ApplicationDoneButton(
                                        text = "수강 신청 완료",
                                        modifier = modifier
                                            .padding(bottom = 40.dp)
                                            .fillMaxWidth()
                                            .height(52.dp)
                                            .align(Alignment.BottomCenter),
                                        onClick = {
                                            isNegativeDialogVisible.value = true
                                        }
                                    )
                                }

                                false -> {
                                    BitgoeulButton(
                                        text = "수강 신청 하기",
                                        modifier = modifier
                                            .padding(bottom = 40.dp)
                                            .fillMaxWidth()
                                            .height(52.dp)
                                            .align(Alignment.BottomCenter),
                                        state = when (isPositiveActionDialogVisible.value) {
                                            true -> ButtonState.Disable
                                            false -> ButtonState.Enable
                                        }
                                    ) {
                                        isPositiveActionDialogVisible.value =
                                            !isPositiveActionDialogVisible.value
                                    }
                                }
                            }
                        }
                    }
                }

                LectureStatus.CLOSE -> {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            BitgoeulButton(
                                text = "수강 신청 불가",
                                modifier = modifier
                                    .padding(bottom = 40.dp)
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .align(Alignment.BottomCenter),
                                state = ButtonState.Disable,
                            ) {}
                        }
                    }
                }
            }
        }

        PositiveActionDialog(
            title = "수강 신청하시겠습니까?",
            content = data.name,
            positiveAction = "신청",
            onQuit = {
                isPositiveActionDialogVisible.value = false
            },
            onActionClicked = {
                onApplicationClick()
                isPositiveActionDialogVisible.value = false
                isApplicationState.value = true
            },
            isVisible = isPositiveActionDialogVisible.value,
        )

        NegativeActionDialog(
            title = "수강 취소하시겠습니까?",
            negativeAction = "확인",
            content = data.content,
            isVisible = isNegativeDialogVisible.value,
            onQuit = { isNegativeDialogVisible.value = false },
            onActionClicked = {
                onApplicationCancelClick()
                isNegativeDialogVisible.value = false
            }
        )

        LectureExcelDownloadBottomSheet(
            isVisible = isExcelDownloadSheetVisible.value,
            onDownloadButtonClick = {
                onDownloadButtonClick()
                isExcelDownloadSheetVisible.value = false
            },
            onQuit = {
                isExcelDownloadSheetVisible.value = false
            }
        )
    }
}