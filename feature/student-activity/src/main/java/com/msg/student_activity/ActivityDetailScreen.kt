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
import androidx.compose.ui.tooling.preview.Preview
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
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.response.activity.InquiryDetailStudentActivityInfoResponse
import com.msg.student_activity.util.Event
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import com.msg.ui.util.toDotFormat
import com.msg.ui.util.toKoreanFormat
import java.time.LocalDate
import java.time.LocalDateTime
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
    viewModel.inquiryDetailStudentActivity(id = id)
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
    onSuccess: (data: InquiryDetailStudentActivityInfoResponse) -> Unit
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
    data: InquiryDetailStudentActivityInfoResponse,
    role: Authority = Authority.ROLE_USER,
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
                if (role == Authority.ROLE_TEACHER) {
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
                } else if (role == Authority.ROLE_STUDENT) {
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
            title = if (role == Authority.ROLE_STUDENT) "활동 삭제하시겠습니까?" else "활동 거부하시겠습니까?",
            negativeAction = if (role == Authority.ROLE_STUDENT) "삭제" else "거부",
            content = data.title,
            isVisible = isNegativeDialogShow.value,
            onQuit = { isNegativeDialogShow.value = false },
            onActionClicked = {
                if (role == Authority.ROLE_STUDENT) onDeleteClicked(data.id) else onRejectClicked(data.id)
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

@Preview
@Composable
fun ActivityDetailScreenPre() {
    ActivityDetailScreen(
        data = InquiryDetailStudentActivityInfoResponse(
            id = UUID.randomUUID(),
            title = "국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다.",
            content = "청춘! 이는 듣기만 하여도 가슴이 설레는 말이다. 청춘! 너의 두 손을 가슴에 대고, 물방아 같은 심장의 고동을 들어 보라. 청춘의 피는 끓는다. 끓는 피에 뛰노는 심장은 거선(巨船)의 기관(汽罐)같이 힘있다. 이것이다. 인류의 역사를 꾸며 내려온 동력은 바로 이것이다. 이성(理性)은 투명하되 얼음과 같으며, 지혜는 날카로우나 갑 속에 든 칼이다. 청춘의 끓는 피가 아니더면, 인간이 얼마나 쓸쓸하랴? 얼음에 싸인 만물(萬物)은 죽음이 있을 뿐이다.\\n\\n그들에게 생명을 불어넣는 것은 따뜻한 봄바람이다. 풀밭에 속잎 나고, 가지에 싹이 트고, 꽃 피고 새 우는 봄날의 천지는 얼마나 기쁘며, 얼마나 아름다우냐? 이것을 얼음 속에서 불러내는 것이 따뜻한 봄바람이다. 인생에 따뜻한 봄바람을 불어 보내는 것은 청춘의 끓는 피다. 청춘의 피가 뜨거운지라, 인간의 동산에는 사랑의 풀이 돋고, 이상(理想)의 꽃이 피고, 희망(希望)의 놀고 뜨고, 열락(悅樂)의 새가 운다.\\n\\n사랑의 풀이 없으면 인간은 사막이다. 오아시스도 없는 사막이다. 보이는 끝까지 찾아다녀도, 목숨이 있는 때까지 방황하여도, 보이는 것은 거친 모래뿐일 것이다. 이상의 꽃이 없으면, 쓸쓸한 인간에 남는 것은 영락(零落)과 부패(腐敗) 뿐이다. 낙원을 장식하는 천자만홍(千紫萬紅)이 어디 있으며, 인생을 풍부하게 하는 온갖 과실이 어디 있으랴?\\n\\n이상! 우리의 청춘이 가장 많이 품고 있는 이상! 이것이야말로 무한한 가치를 가진 것이다. 사람은 크고 작고 간에 이상이 있음으로써 용감하고 굳세게 살 수 있는 것이다.\\n\\n석가(釋迦)는 무엇을 위하여 설산(雪山)에서 고행(苦行)을 하였으며, 예수는 무엇을 위하여 광야(曠野)에서 방황하였으며, 공자는 무엇을 위하여 천하를 철환(轍環)하였는가? 밥을 위하여서, 옷을 위하여서, 미인(美人)을 구하기 위하여서 그리하였는가? 아니다. 그들은 커다란 이상, 곧 만천하(萬天下)의 대중(大衆)을 품에 안고, 그들에게 밝은 길을 찾아 주며, 그들을 행복스럽고 평화스러운 곳으로 인도하겠다는, 커다란 이상을 품었기 때문이다. 그러므로 그들은 길지 아니한 목숨을 사는가 싶이 살았으며, 그들의 그림자는 천고에 사라지지 않는 것이다. 이것은 가장 현저하여 일월과 같은 예가 되려니와, 그와 같지 못하다 할지라도 창공에 반짝이는 뭇 별과 같이, 산야(山野)에 피어나는 군영(群英)과 같이, 이상은 실로 인간의 부패를 방지하는 소금이라 할지니, 인생에 가치를 주는 원질(原質)이 되는 것이다.\\n\\n이상! 빛나는 귀중한 이상, 이것은 청춘의 누리는 바 특권이다. 그들은 순진한지라 감동하기 쉽고, 그들은 점염(點染)이 적은지라 죄악에 병들지 아니하였고, 그들은 앞이 긴지라 착목(着目)하는 곳이 원대하고, 그들은 피가 더운지라 실현에 대한 자신과 용기가 있다. 그러므로 그들은 이상의 보배를 능히 품으며, 그들의 이상은 아름답고 소담스러운 열매를 맺어, 우리 인생을 풍부하게 하는 것이다.\\n\\n보라, 청춘을! 그들의 몸이 얼마나 튼튼하며, 그들의 피부가 얼마나 생생하며, 그들의 눈에 무엇이 타오르고 있는가? 우리 눈이 그것을 보는 때에, 우리의 귀는 생(生)의 찬미(讚美)를 듣는다. 뼈 끝에 스며들어 가는 열락의 소리다.\\n\\n이것은 피어나기 전인 유소년(幼少年)에게서 구하지 못할 바이며, 시들어 가는 노년(老年)에게서 구하지 못할 바이며, 오직 우리 청춘에서만 구할 수 있는 것이다.\\n\\n청춘은 인생의 황금 시대(黃金時代)다. 우리는 이 황금 시대의 가치를 충분히 발휘하기 위하여, 이 황금 시대를 영원히 붙잡아 두기 위하여, 힘차게 노래하며 힘차게 약동하자!",
            credit = 2,
            activityDate = LocalDate.now(),
            modifiedAt = LocalDateTime.now(),
            approveState = ApproveStatus.PENDING
        ),
        role = Authority.ROLE_STUDENT,
        onRejectClicked = {},
        onDeleteClicked = {},
        onApproveClicked = {},
        onActionEnd = {},
        onEditClicked = {},
        onBackClicked = {}
    )
}