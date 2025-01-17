package com.msg.certification

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msg.certification.component.CertificationSection
import com.msg.certification.component.FinishedLectureSection
import com.msg.certification.component.StudentInfoSection
import com.msg.common.event.Event
import com.msg.certification.viewmodel.CertificationViewModel
import com.msg.certification.viewmodel.uistate.GetCertificationListUiState
import com.msg.certification.viewmodel.uistate.GetLectureSignUpHistoryUiState
import com.msg.certification.viewmodel.uistate.GetStudentBelongClubDetailUiState
import com.msg.design_system.R
import com.msg.design_system.component.icon.HumanIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.certification.CertificationListEntity
import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.entity.lecture.SignUpLectures
import com.msg.ui.DevicePreviews
import com.msg.ui.makeToast
import java.time.LocalDate
import java.util.UUID

@Composable
internal fun CertificationScreenRoute(
    viewModel: CertificationViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onHumanIconClicked: () -> Unit,
    onEditClicked: () -> Unit,
    createErrorToast: (throwable: Throwable?, message: Int?) -> Unit
) {
    val getCertificationListUiState by viewModel.getCertificationListUiState.collectAsStateWithLifecycle()
    val getLectureSignUpHistoryUiState by viewModel.getLectureSignUpHistoryUiState.collectAsStateWithLifecycle()
    val getStudentBelongClubDetailUiState by viewModel.getStudentBelongClubDetailUiState.collectAsStateWithLifecycle()

    viewModel.getCertificationList()
    viewModel.getStudentBelong()
    viewModel.getLectureSignUpHistory()

    LaunchedEffect(true) {
        getCertificationList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.certificationList.addAll(it)
            },
            onFailure = {}
        )
        getStudentData(
            viewModel = viewModel,
            onSuccess = {
                viewModel.studentData.value = it
            },
            onFailure = {}
        )
        getLectureData(
            viewModel = viewModel,
            onSuccess = {
                viewModel.lectureData.value = it
            },
            onFailure = {}
        )
    }

    CertificationScreen(
        onHumanIconClicked = onHumanIconClicked,
        onEditClicked = { id, title, date ->
            viewModel.selectedCertificationId.value = id
            viewModel.onSelectedTitleChange(title)
            viewModel.selectedDate.value = date
            onEditClicked()
        },
        onPlusClicked = onEditClicked,
        studentData = viewModel.studentData.value,
        certificationData = viewModel.certificationList,
        lectureData = viewModel.lectureData.value,
        getCertificationListUiState = getCertificationListUiState,
        getLectureSignUpHistoryUiState = getLectureSignUpHistoryUiState,
        getStudentBelongClubDetailUiState = getStudentBelongClubDetailUiState,
        createErrorToast = createErrorToast
    )
}

private suspend fun getCertificationList(
    viewModel: CertificationViewModel,
    onSuccess: (data: List<CertificationListEntity>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCertificationListResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

private suspend fun getStudentData(
    viewModel: CertificationViewModel,
    onSuccess: (data: StudentBelongClubEntity) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getStudentBelongResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

private suspend fun getLectureData(
    viewModel: CertificationViewModel,
    onSuccess: (data: GetLectureSignUpHistoryEntity) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getLectureSignUpHistoryResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

@Composable
internal fun CertificationScreen(
    modifier: Modifier = Modifier,
    createErrorToast: (throwable: Throwable?, message: Int?) -> Unit,
    getCertificationListUiState: GetCertificationListUiState,
    getLectureSignUpHistoryUiState: GetLectureSignUpHistoryUiState,
    getStudentBelongClubDetailUiState: GetStudentBelongClubDetailUiState,
    onHumanIconClicked: () -> Unit,
    onEditClicked: (id: UUID, title: String, date: LocalDate) -> Unit,
    onPlusClicked: () -> Unit,
    studentData: StudentBelongClubEntity,
    certificationData: List<CertificationListEntity>,
    lectureData: GetLectureSignUpHistoryEntity,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            val context = LocalContext.current
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(24.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.student_information),
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(
                        modifier = modifier.size(24.dp),
                        content = { HumanIcon() },
                        onClick = onHumanIconClicked
                    )
                }
                Spacer(modifier = modifier.height(24.dp))
                when(getStudentBelongClubDetailUiState) {
                    is GetStudentBelongClubDetailUiState.Success -> { StudentInfoSection(data = studentData) }
                    is GetStudentBelongClubDetailUiState.Loading -> {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = modifier.then(Modifier.size(27.dp)),
                                color = colors.G2,
                                strokeWidth = 2.dp
                            )
                        }
                    }
                    is GetStudentBelongClubDetailUiState.Error -> {
                        createErrorToast(getStudentBelongClubDetailUiState.expectation, R.string.fail_get_student_club_detail)
                    }
                }
                Spacer(modifier = modifier.height(24.dp))
                HorizontalDivider(
                    modifier = modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = colors.G9
                )
                Spacer(modifier = modifier.height(24.dp))
                when(getCertificationListUiState) {
                    is GetCertificationListUiState.Success -> {
                        CertificationSection(
                            onPlusClicked = onPlusClicked,
                            onEditClicked = { id, title, date ->
                                onEditClicked(id, title, date)
                            },
                            data = certificationData
                        )
                    }
                    is GetCertificationListUiState.Loading -> {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = modifier.then(Modifier.size(27.dp)),
                                color = colors.G2,
                                strokeWidth = 2.dp
                            )
                        }
                    }
                    is GetCertificationListUiState.Error -> {
                        createErrorToast(getCertificationListUiState.expectation, R.string.fail_get_certification_list)
                    }
                    is GetCertificationListUiState.Empty -> {
                        makeToast(context, R.string.empty_state.toString())
                    }
                }
                Spacer(modifier = modifier.height(12.dp))
                when(getLectureSignUpHistoryUiState) {
                    is GetLectureSignUpHistoryUiState.Success -> { FinishedLectureSection(data = lectureData) }
                    is GetLectureSignUpHistoryUiState.Loading -> {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = modifier.then(Modifier.size(27.dp)),
                                color = colors.G2,
                                strokeWidth = 2.dp
                            )
                        }
                    }
                    is GetLectureSignUpHistoryUiState.Error -> {
                        createErrorToast(getLectureSignUpHistoryUiState.expectation, R.string.fail_get_lecture_list)
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun CertificationScreenPre() {
    CertificationScreen(
        onHumanIconClicked = {},
        onEditClicked = {_,_,_->},
        onPlusClicked = {},
        studentData = StudentBelongClubEntity(
            name = "채종인",
            phoneNumber = "010-1234-5678",
            email = "s22055@gsm.hs.kr",
            credit = 300
        ),
        certificationData = listOf(
            CertificationListEntity(
                certificationId = UUID.randomUUID(),
                name = "정보처리산업기사",
                acquisitionDate = LocalDate.now()
            )
        ),
        lectureData = GetLectureSignUpHistoryEntity(
            lectures = listOf(
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "개쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                ),
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "덜쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                )
            )
        ),
        getCertificationListUiState = GetCertificationListUiState.Empty,
        getLectureSignUpHistoryUiState = GetLectureSignUpHistoryUiState.Error(expectation = Throwable("통신이 원할하지 않습니다.")),
        getStudentBelongClubDetailUiState = GetStudentBelongClubDetailUiState.Error(expectation = Throwable("통신이 원할하지 않습니다.")),
        createErrorToast = { _, _ -> }
    )
}