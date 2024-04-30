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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import com.msg.design_system.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.AddLectureDatesButton
import com.msg.lecture.component.LectureDetailSettingInputTextField
import com.msg.lecture.component.LectureDetailSettingSearchBottomSheet
import com.msg.lecture.component.LectureDetailSettingSection
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.model.lecture.LectureDates
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import com.msg.ui.util.toKoreanFormat
import com.msg.ui.util.toLocalTimeFormat
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

@Composable
internal fun LectureDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val coroutineScope = rememberCoroutineScope()
    LectureDetailSettingScreen(
        onCloseClicked = {
            onCloseClicked()
            viewModel.saveLectureDatesList()
        },
        onApplyClicked = { lectureType, semester, division, department, line, userId, credit, startDate, endDate, maxRegisteredUser ->
            viewModel.lectureType.value = lectureType
            viewModel.semester.value = semester
            viewModel.division.value = division
            viewModel.department.value = department
            viewModel.line.value = line
            viewModel.userId.value = userId
            viewModel.credit.value = credit
            viewModel.endDate.value = endDate
            viewModel.startDate.value = startDate
            viewModel.maxRegisteredUser.value = maxRegisteredUser
            onApplyClicked()
        },
        onSearchProfessorClicked = { keyword ->
            viewModel.searchProfessor(keyword = keyword)
            coroutineScope.launch {
                getProfessorSearchData(viewModel = viewModel, onSearchProfessorSuccess = { data ->
                    viewModel.searchProfessorData.value = data
                })
            }
        },
        onSearchLineClicked = { keyword, division ->
            viewModel.searchLine(keyword = keyword, division = division)
            coroutineScope.launch {
                getLineSearchData(viewModel = viewModel, onLineSuccess = { data ->
                    viewModel.searchLineData.value = data
                })
            }
        },
        onSearchDepartmentClicked = { keyword ->
            viewModel.searchDepartment(keyword = keyword)
            coroutineScope.launch {
                getDepartmentSearchData(viewModel = viewModel, onDepartmentSuccess = { data ->
                    viewModel.searchDepartmentData.value = data
                })
            }

        },
        onLectureDatesAddClicked = {
            viewModel.addLectureDates()
        },
        onLectureDatesRemoveClicked = {
            viewModel.removeLectureDates()
        },
        onCompleteDateChanged = { completeDate ->
            viewModel.completeDate.value = completeDate
        },
        onStartTimeChanged = { starTime ->
            viewModel.startTime.value = starTime
        },
        onEndTimeChanged = { endTime ->
            viewModel.endTime.value = endTime
        },
        lectureDates = viewModel.lectureDates,
        startDateForConversion = viewModel.startDateForConversion.value,
        endDateForConversion = viewModel.endDateForConversion.value,
        searchLineData = viewModel.searchLineData.value,
        searchProfessorData = viewModel.searchProfessorData.value,
        searchDepartmentData = viewModel.searchDepartmentData.value,
        savedCreditPoint = viewModel.credit.value,
        savedStartTime = viewModel.startTime.value,
        savedEndTime = viewModel.endTime.value,
        savedCompleteDate = viewModel.completeDate.value,
        savedStartDate = viewModel.startDate.value,
        savedEndDate = viewModel.endDate.value,
        savedMaxRegisteredUser = viewModel.maxRegisteredUser.value,
        savedSemester = viewModel.semester.value,
        savedDivision = viewModel.division.value,
        savedDepartment = viewModel.department.value,
        savedLine = viewModel.line.value,
        savedUserId = viewModel.userId.value,
        savedLectureType = viewModel.lectureType.value,
    )
}


suspend fun getLineSearchData(
    viewModel: LectureViewModel,
    onLineSuccess: (data: SearchLineResponse) -> Unit,
) {
    viewModel.searchLineResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onLineSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

suspend fun getProfessorSearchData(
    viewModel: LectureViewModel,
    onSearchProfessorSuccess: (data: SearchProfessorResponse) -> Unit
) {
    viewModel.searchProfessorResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSearchProfessorSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

suspend fun getDepartmentSearchData(
    viewModel: LectureViewModel,
    onDepartmentSuccess: (data: SearchDepartmentResponse) -> Unit,
) {
    viewModel.searchDepartmentResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onDepartmentSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
fun LectureDetailSettingScreen(
    modifier: Modifier = Modifier,
    searchProfessorData: SearchProfessorResponse,
    searchLineData: SearchLineResponse,
    searchDepartmentData: SearchDepartmentResponse,
    onCloseClicked: () -> Unit,
    onLectureDatesAddClicked: () -> Unit,
    onLectureDatesRemoveClicked: () -> Unit,
    onApplyClicked: (String, String, String, String, String, UUID, Int, LocalDateTime?, LocalDateTime?, Int) -> Unit,
    onSearchProfessorClicked: (String) -> Unit,
    onSearchLineClicked: (String, String) -> Unit,
    onSearchDepartmentClicked: (String) -> Unit,
    onCompleteDateChanged: (completeDate: LocalDate) -> Unit,
    onStartTimeChanged: (startTIme: LocalTime) -> Unit,
    onEndTimeChanged: (endTime: LocalTime) -> Unit,
    lectureDates: MutableList<LectureDates>,
    startDateForConversion: LocalDate?,
    endDateForConversion: LocalDate?,
    savedLectureType: String,
    savedSemester: String,
    savedDivision: String,
    savedDepartment: String,
    savedLine: String,
    savedUserId: UUID,
    savedCreditPoint: Int,
    savedStartDate: LocalDateTime?,
    savedEndDate: LocalDateTime?,
    savedCompleteDate: LocalDate?,
    savedStartTime: LocalTime?,
    savedEndTime: LocalTime?,
    savedMaxRegisteredUser: Int,
) {
    val isSearchBottomSheetVisible = remember { mutableStateOf(false) }
    val isClickedPickerType = remember { mutableStateOf("") }
    val isLectureCategoryTagSelected = remember { mutableStateOf("0") }
    val isLectureSemesterAttendedTagSelected = remember { mutableStateOf("0") }
    val isLectureDivisionTagSelected = remember { mutableStateOf("0") }
    val isLectureCreditTagSelected = remember { mutableStateOf("0") }
    val isShowDeleteIcon = remember { mutableStateOf(false) }

    val semesterList = listOf("1학년 2학기", "2학년 1학기", "2학년 2학기", "3학년 1학기")
    val lectureTypeList = listOf("상호학점인정교육과정", "유관기관프로그램", "대학탐방프로그램", "기업산학연계직업체험프로그램", "기타")
    val divisionList = listOf("자동차 산업", "에너지 산업", "의료•헬스케어", "AI융•복합", "문화 산업")

    val lectureType = remember { mutableStateOf(savedLectureType) }
    val division = remember { mutableStateOf(savedDivision) }
    val department = remember { mutableStateOf(savedDepartment) }
    val line = remember { mutableStateOf(savedLine) }
    val userId = remember { mutableStateOf(savedUserId) }
    val credit = remember { mutableIntStateOf(savedCreditPoint) }
    val semester = remember { mutableStateOf(savedSemester) }
    val startDate = remember { mutableStateOf(savedStartDate) }
    val endDate = remember { mutableStateOf(savedEndDate) }
    val startDateForConversion = remember { mutableStateOf(startDateForConversion) }
    val endDateForConversion = remember { mutableStateOf(endDateForConversion) }
    val startTime = remember { mutableStateOf(savedStartTime) }
    val endTime = remember { mutableStateOf(savedEndTime) }
    val maxRegisteredUser = remember { mutableIntStateOf(savedMaxRegisteredUser) }

    val completeDatesComponentWeight = remember { mutableFloatStateOf(1f) }

    val applicationStartTimeForShow =
        remember { mutableStateOf(savedStartTime?.toLocalTimeFormat() ?: "") }
    val applicationEndTimeForShow =
        remember { mutableStateOf(savedEndTime?.toLocalTimeFormat() ?: "") }
    val applicationStartDateForShow =
        remember { mutableStateOf(savedStartDate?.toKoreanFormat() ?: "") }
    val applicationEndDateForShow =
        remember { mutableStateOf(savedEndDate?.toKoreanFormat() ?: "") }

    val lectureAttendCompleteDateListForShow =
        remember { mutableStateListOf(savedCompleteDate?.toKoreanFormat() ?: "") }
    val lectureAttendStartTimeListForShow =
        remember { mutableStateListOf(savedStartTime?.toLocalTimeFormat() ?: "") }
    val lectureAttendEndTimeListForShow =
        remember { mutableStateListOf(savedEndTime?.toLocalTimeFormat() ?: "") }


    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .background(color = colors.WHITE)
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp)
        ) {

            item {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.lecture_detail_setting),
                        color = colors.BLACK,
                        style = typography.titleSmall,
                    )

                    CloseIcon(
                        modifier = modifier.clickable {
                            onCloseClicked()
                        }
                    )
                }

                Spacer(modifier = modifier.height(28.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.semester_attended),
                    list = semesterList,
                    selectedItem = semester.value.ifEmpty { "학기 선택" },
                    onItemChange = { selectedSemester ->
                        if (semester.value != selectedSemester) semester.value =
                            selectedSemester else semester.value = "학기 선택"
                    },
                    type = "Selector"
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.lecture_category),
                    list = lectureTypeList,
                    selectedItem = lectureType.value.ifEmpty { "유형 선택" },
                    onItemChange = { selectedLectureType ->
                        if (lectureType.value != selectedLectureType) lectureType.value =
                            selectedLectureType else lectureType.value = "유형 선택"
                    },
                    type = "Selector"
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.lecture_division),
                    list = divisionList,
                    selectedItem = division.value.ifEmpty { "구분 선택" },
                    onItemChange = { selectedDivision ->
                        if (division.value != selectedDivision) division.value =
                            selectedDivision else division.value = "구분 선택"
                    },
                    type = "Selector"
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.lecture_series),
                    list = listOf(),
                    selectedItem = "강의 선택",
                    onItemChange = {},
                    type = "Search",
                    onClick = {
                        isSearchBottomSheetVisible.value = true
                        isClickedPickerType.value = "강의 계열"
                    }
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.department),
                    list = listOf(),
                    selectedItem = "학과 선택",
                    onItemChange = {},
                    type = "Search",
                    onClick = {
                        isSearchBottomSheetVisible.value = true
                        isClickedPickerType.value = "학과"
                    }
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.teacher_in_charge),
                    list = listOf(),
                    selectedItem = "담당 강사 선택",
                    onItemChange = {},
                    type = "Search",
                    onClick = {
                        isSearchBottomSheetVisible.value = true
                        isClickedPickerType.value = "담당 교수"
                    }
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                // 입력 가능한 Component 생성 후 변경하기
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.application_start_date),
                    list = listOf(),
                    selectedItem = "예시: ○○○○년 ○○월 ○○일",
                    onItemChange = {},
                    type = "Input"
                )

                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = "",
                    list = listOf(),
                    selectedItem = "○○시 ○○분",
                    onItemChange = {},
                    type = "Input"
                )

                Spacer(modifier = modifier.height(24.dp))

            }

            item {
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = stringResource(id = R.string.application_deadline_date),
                    list = listOf(),
                    selectedItem = "예시: ○○○○년 ○○월 ○○일",
                    onItemChange = {},
                    type = "Input"
                )

                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = "",
                    list = listOf(),
                    selectedItem = "○○시 ○○분",
                    onItemChange = {},
                    type = "Input"
                )

                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = stringResource(id = R.string.lecture_attendance_date),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            itemsIndexed(lectureDates) { index, lectureDatesItem ->
                LectureDetailSettingSection(
                    modifier = modifier.fillMaxWidth(),
                    subjectText = "",
                    list = listOf(),
                    selectedItem = "",
                    onItemChange = {},
                    type = "LectureDates"
                )
            }

            item {
                AddLectureDatesButton(
                    modifier = modifier.fillMaxWidth()
                ) {

                }
            }

            item {
                LectureDetailSettingInputTextField(
                    modifier = modifier,
                    placeholder = stringResource(id = R.string.enter_maximum_number_students),
                    subjectText = stringResource(id = R.string.maximum_number_students)
                )
            }
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = colors.WHITE)
            ) {
                BitgoeulButton(
                    text = stringResource(id = R.string.apply),
                    modifier = modifier
                        .padding(bottom = 14.dp, top = 16.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 24.dp),
                ) {
                    if (lectureDates.size == 0) {
                        onLectureDatesAddClicked()
                    }

                    onApplyClicked(
                        lectureType.value,
                        semester.value,
                        division.value,
                        department.value,
                        line.value,
                        userId.value,
                        credit.value,
                        startDate.value,
                        endDate.value,
                        maxRegisteredUser.value,
                    )
                }
            }
        }
        LectureDetailSettingSearchBottomSheet(
            isVisible = isSearchBottomSheetVisible.value,
            searchPlaceHolder = when (isClickedPickerType.value) {
                "강의 계열" -> stringResource(id = R.string.lecture_series_placeholder)
                "학과" -> stringResource(id = R.string.department_placeholder)
                "담당 교수" -> stringResource(id = R.string.professor_in_charge_placeholder)
                else -> ""
            },
            onSearchButtonClick = { keyword, division ->
                when (isClickedPickerType.value) {
                    "강의 계열" -> {
                        onSearchLineClicked(keyword, division)
                    }

                    "학과" -> {
                        onSearchDepartmentClicked(keyword)
                    }

                    "담당 교수" -> {
                        onSearchProfessorClicked(keyword)
                    }

                    else -> {}
                }
            },
            searchAPIType = when (isClickedPickerType.value) {
                "학과" -> "학과"
                "강의 계열" -> "강의 계열"
                "담당 교수" -> "담당 교수"
                else -> ""
            },
            onProfessorListClick = { selectedProfessorUUID ->
                userId.value = selectedProfessorUUID
            },
            division = division.value,
            searchProfessorData = searchProfessorData,
            searchLineData = searchLineData,
            searchDepartmentData = searchDepartmentData,
            onDepartmentListClick = { selectedDepartmentData ->
                department.value = selectedDepartmentData
            },
            onLineListClick = { selectedLineData ->
                line.value = selectedLineData
            },
            onQuit = {
                isSearchBottomSheetVisible.value = false
            }
        )
    }
}