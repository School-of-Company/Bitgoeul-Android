package com.msg.student_activity

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.NegativeBitgoeulButton
import com.msg.design_system.component.dialog.NegativeActionDialog
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.student_activity.util.Event
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import com.msg.ui.util.toDotFormat
import com.msg.ui.util.toKoreanFormat
import java.util.UUID

@Composable
fun ActivityDetailRoute(
    onActionEnd: () -> Unit,
    onEditClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: StudentActivityViewModel = hiltViewModel()
) {
    val role = viewModel.role
    val id = viewModel.selectedActivityId.value
    viewModel.getDetailStudentActivity(id = id)
    LaunchedEffect(true) {
        getActivityData(
            viewModel = viewModel,
            onSuccess = {
                viewModel.studentDetailActivityData.value = it
            }
        )
    }
    ActivityDetailScreen(
        data = viewModel.studentDetailActivityData.value,
        role = role,
        onDeleteClicked = { viewModel.deleteActivityInfo(it) },
        onRejectClicked = { viewModel.rejectActivityInfo(it) },
        onApproveClicked = { viewModel.approveActivityInfo(it) },
        onActionEnd = onActionEnd,
        onEditClicked = onEditClicked,
        onBackClicked = onBackClicked
    )
}

suspend fun getActivityData(
    viewModel: StudentActivityViewModel,
    onSuccess: (data: GetDetailStudentActivityInfoResponse) -> Unit
) {
    viewModel.getDetailStudentActivityResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
fun ActivityDetailScreen(
    data: GetDetailStudentActivityInfoResponse,
    role: String = Authority.ROLE_USER.toString(),
    onDeleteClicked: (UUID) -> Unit,
    onRejectClicked: (UUID) -> Unit,
    onApproveClicked: (UUID) -> Unit,
    onActionEnd: () -> Unit,
    onEditClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val scrollState = rememberScrollState()

    val isNegativeDialogShow = remember { mutableStateOf(false) }
    val isPositiveDialogShow = remember { mutableStateOf(false) }
    val whichNegative = remember { mutableStateOf("") }

    BitgoeulAndroidTheme { colors, typography ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (data.approveState == ApproveStatus.APPROVED) {
                            Text(
                                text = "승인됨",
                                style = typography.labelMedium,
                                color = colors.P5
                            )
                        } else {
                            Text(
                                text = "승인 대기 중",
                                style = typography.labelMedium,
                                color = colors.E5
                            )
                        }
                        Text(
                            text = "${data.modifiedAt.toKoreanFormat()}에 수정됨",
                            style = typography.labelMedium,
                            color = colors.G1
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = data.title,
                        style = typography.bodyLarge,
                        color = colors.BLACK
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${data.activityDate.toDotFormat()} 활동",
                            style = typography.bodySmall,
                            color = colors.G2
                        )
                        Text(
                            text = "학점 ${data.credit}점 부여",
                            style = typography.bodySmall,
                            color = colors.G2
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = data.content,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 32.sp,
                        fontFamily = FontFamily(Font(com.msg.design_system.R.font.pretendard_regular)),
                        color = colors.BLACK
                    )
                    Spacer(modifier = Modifier.height(68.dp))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 28.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (role == Authority.ROLE_TEACHER.toString()) {
                    Row(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        NegativeBitgoeulButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "활동 거부"
                        ) {
                            whichNegative.value = "reject"
                            isNegativeDialogShow.value = true
                        }
                    }
                    Row(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        BitgoeulButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "활동 승인"
                        ) {
                            isPositiveDialogShow.value = true
                        }
                    }
                } else if (role == Authority.ROLE_STUDENT.toString()) {
                    Row(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        BitgoeulButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "활동 수정"
                        ) {
                            onEditClicked()
                        }
                    }
                    Row(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        NegativeBitgoeulButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "활동 삭제"
                        ) {
                            isNegativeDialogShow.value = true
                            whichNegative.value = "delete"
                        }
                    }
                }
            }
        }
        NegativeActionDialog(
            title = if (role == Authority.ROLE_STUDENT.toString()) "활동 삭제하시겠습니까?" else "활동 거부하시겠습니까?",
            negativeAction = if (role == Authority.ROLE_STUDENT.toString()) "삭제" else "거부",
            content = data.title,
            isVisible = isNegativeDialogShow.value,
            onQuit = { isNegativeDialogShow.value = false },
            onActionClicked = {
                if (role == Authority.ROLE_STUDENT.toString()) onDeleteClicked(data.id) else onRejectClicked(data.id)
                onActionEnd()
            }
        )

        PositiveActionDialog(
            title = "활동 승인하시겠습니까?",
            positiveAction = "승인",
            content = data.title,
            isVisible = isPositiveDialogShow.value,
            onQuit = { isPositiveDialogShow.value = false },
            onActionClicked = {
                onApproveClicked(data.id)
                onActionEnd()
            }
        )
    }
}