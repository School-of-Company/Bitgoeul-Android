package com.msg.student_activity

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import com.msg.model.enumdata.Authority
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.NegativeBitgoeulButton
import com.msg.design_system.component.dialog.NegativeActionDialog
import com.msg.design_system.component.dialog.PositiveActionDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.activity.GetDetailStudentActivityInfoEntity
import com.msg.model.enumdata.ApproveStatus
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import com.msg.ui.util.toDotFormat
import com.msg.ui.util.toKoreanFormat
import java.util.UUID

@Composable
fun ActivityDetailRoute(
    viewModel: StudentActivityViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onActionEnd: () -> Unit,
    onEditClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val role = viewModel.role
    val id = viewModel.selectedActivityId.value

    viewModel.getDetailStudentActivity(id = id)
    LaunchedEffect(true) {
        getActivityData(
            viewModel = viewModel,
            onSuccess = { viewModel.studentDetailActivityData.value = it }
        )
    }
    ActivityDetailScreen(
        data = viewModel.studentDetailActivityData.value,
        role = role,
        onWhichNegativeChange = viewModel::onWhichNegativeChange,
        onDeleteClicked = viewModel::deleteActivityInfo,
        onRejectClicked = viewModel::rejectActivityInfo,
        onApproveClicked = viewModel::approveActivityInfo,
        onActionEnd = onActionEnd,
        onEditClicked = onEditClicked,
        onBackClicked = onBackClicked
    )
}

suspend fun getActivityData(
    viewModel: StudentActivityViewModel,
    onSuccess: (data: GetDetailStudentActivityInfoEntity) -> Unit
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
    modifier: Modifier = Modifier,
    data: GetDetailStudentActivityInfoEntity,
    role: String = Authority.ROLE_USER.toString(),
    scrollState: ScrollState = rememberScrollState(),
    onWhichNegativeChange: (String) -> Unit,
    onDeleteClicked: (UUID) -> Unit,
    onRejectClicked: (UUID) -> Unit,
    onApproveClicked: (UUID) -> Unit,
    onActionEnd: () -> Unit,
    onEditClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val (isNegativeDialogShow, isSetNegativeDialogShow) = rememberSaveable { mutableStateOf(false) }
    val (isPositiveDialogShow, isSetPositionDialogShow) = rememberSaveable { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Column(
                    modifier = modifier
                        .padding(horizontal = 28.dp)
                ) {
                    Spacer(modifier = modifier.height(24.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
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
                    Spacer(modifier = modifier.height(4.dp))
                    Text(
                        text = data.title,
                        style = typography.bodyLarge,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
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
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        text = data.content,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 32.sp,
                        fontFamily = FontFamily(Font(com.msg.design_system.R.font.pretendard_regular)),
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(68.dp))
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 28.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (role == Authority.ROLE_TEACHER.toString()) {
                    Row(
                        modifier = modifier.weight(0.45f)
                    ) {
                        NegativeBitgoeulButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "활동 거부"
                        ) {
                            onWhichNegativeChange("reject")
                            isSetNegativeDialogShow(true)
                        }
                    }
                    Row(
                        modifier = modifier.weight(0.45f)
                    ) {
                        BitgoeulButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "활동 승인"
                        ) {
                            isSetPositionDialogShow(true)
                        }
                    }
                } else if (role == Authority.ROLE_STUDENT.toString()) {
                    Row(
                        modifier = modifier.weight(0.45f)
                    ) {
                        BitgoeulButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "활동 수정"
                        ) {
                            onEditClicked()
                        }
                    }
                    Row(modifier = modifier.weight(0.45f)) {
                        NegativeBitgoeulButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "활동 삭제"
                        ) {
                            isSetNegativeDialogShow(true)
                            onWhichNegativeChange("delete")
                        }
                    }
                }
            }
        }
        NegativeActionDialog(
            title = if (role == Authority.ROLE_STUDENT.toString()) "활동 삭제하시겠습니까?" else "활동 거부하시겠습니까?",
            negativeAction = if (role == Authority.ROLE_STUDENT.toString()) "삭제" else "거부",
            content = data.title,
            isVisible = isNegativeDialogShow,
            onQuit = { isSetNegativeDialogShow(false) },
            onActionClicked = {
                if (role == Authority.ROLE_STUDENT.toString()) onDeleteClicked(data.id) else onRejectClicked(data.id)
                onActionEnd()
            }
        )

        PositiveActionDialog(
            title = "활동 승인하시겠습니까?",
            positiveAction = "승인",
            content = data.title,
            isVisible = isPositiveDialogShow,
            onQuit = { isSetPositionDialogShow(false) },
            onActionClicked = {
                onApproveClicked(data.id)
                onActionEnd()
            }
        )
    }
}