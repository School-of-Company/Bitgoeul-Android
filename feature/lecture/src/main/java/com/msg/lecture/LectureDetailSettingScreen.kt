package com.msg.lecture

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.msg.design_system.component.icon.DeleteIcon
import com.msg.design_system.component.text.BitgoeulSubjectText
import com.msg.design_system.component.textfield.LectureDetailSettingTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.AddLectureDatesButton
import com.msg.lecture.component.LectureDetailSettingInputTextField
import com.msg.lecture.component.LectureDetailSettingLectureDatesTextField
import com.msg.lecture.component.LectureDetailSettingSearchBottomSheet
import com.msg.lecture.component.LectureDetailSettingSearchTextField
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.model.lecture.LectureDates
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

@Composable
internal fun LectureDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
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
        onLectureDatesChanged = { completeDate, startTIme, endTime ->
            viewModel.completeDate.value = completeDate
            viewModel.startTime.value = startTIme
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
    onSearchProfessorSuccess: (data: SearchProfessorResponse) -> Unit,
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
    onLectureDatesChanged: (completeDate: LocalDate, startTIme: LocalTime, endTime: LocalTime) -> Unit,
    lectureDates: List<LectureDates>,
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

    val applicationStartTimeForShow = remember { mutableStateOf("") }
    val applicationEndTimeForShow = remember { mutableStateOf("") }
    val applicationStartDateForShow = remember { mutableStateOf("") }
    val applicationEndDateForShow = remember { mutableStateOf("") }

    val lectureLineForShow = remember { mutableStateOf("") }
    val lectureDepartmentForShow = remember { mutableStateOf("") }
    val lectureTeacherInChargeForShow = remember { mutableStateOf("") }

    val lectureAttendCompleteDateListForShow = remember { mutableStateListOf("") }
    val lectureAttendStartTimeListForShow = remember { mutableStateListOf("") }
    val lectureAttendEndTimeListForShow = remember { mutableStateListOf("") }


    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .wrapContentSize()
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
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.semester_attended),
                )

                LectureDetailSettingTextField(
                    modifier = modifier.fillMaxWidth(),
                    list = semesterList,
                    selectedItem = semester.value.ifEmpty { "학기 선택" },
                    onItemChange = { selectedSemester ->
                        if (semester.value != selectedSemester) semester.value =
                            selectedSemester else semester.value = "학기 선택"
                    },
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.lecture_category),
                )

                LectureDetailSettingTextField(
                    modifier = modifier.fillMaxWidth(),
                    list = lectureTypeList,
                    selectedItem = lectureType.value.ifEmpty { "유형 선택" },
                    onItemChange = { selectedLectureType ->
                        if (lectureType.value != selectedLectureType) lectureType.value =
                            selectedLectureType else lectureType.value = "유형 선택"
                    },
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.lecture_division),
                )

                LectureDetailSettingTextField(
                    modifier = modifier.fillMaxWidth(),
                    list = divisionList,
                    selectedItem = division.value.ifEmpty { "구분 선택" },
                    onItemChange = { selectedDivision ->
                        if (division.value != selectedDivision) division.value =
                            selectedDivision else division.value = "구분 선택"
                    },
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.lecture_series),
                )

                LectureDetailSettingSearchTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = lectureLineForShow.value.ifEmpty { "강의 선택" },
                    division = division.value,
                    onLineItemClick = { selectedLineData ->
                        line.value = selectedLineData
                        lectureLineForShow.value = selectedLineData
                    },
                    isClickedPickerType = "강의 계열",
                    onSearchLineClicked = { keyword, division ->
                        onSearchLineClicked(keyword, division)
                    },
                    searchLineData = searchLineData,
                    searchDepartmentData = searchDepartmentData,
                    searchProfessorData = searchProfessorData,
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.department),
                )

                LectureDetailSettingSearchTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = lectureDepartmentForShow.value.ifEmpty { "학과 선택" },
                    isClickedPickerType = "학과",
                    onDepartmentItemClick = { selectedDepartmentData ->
                        department.value = selectedDepartmentData
                        lectureDepartmentForShow.value = selectedDepartmentData
                    },
                    onSearchDepartmentClicked = { keyword ->
                        onSearchDepartmentClicked(keyword)
                    },
                    searchLineData = searchLineData,
                    searchDepartmentData = searchDepartmentData,
                    searchProfessorData = searchProfessorData,
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.teacher_in_charge),
                )

                LectureDetailSettingSearchTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = lectureTeacherInChargeForShow.value.ifEmpty { "담당 강사 선택" },
                    isClickedPickerType = "담당 교수",
                    onProfessorItemClick = { selectedProfessorUUID, selectedProfessorName ->
                        userId.value = selectedProfessorUUID
                        lectureTeacherInChargeForShow.value = selectedProfessorName
                    },
                    onSearchProfessorClicked = { keyword ->
                        onSearchProfessorClicked(keyword)
                    },
                    searchLineData = searchLineData,
                    searchDepartmentData = searchDepartmentData,
                    searchProfessorData = searchProfessorData,
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.application_start_date),
                )

                LectureDetailSettingInputTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "예시: ○○○○년 ○○월 ○○일",
                    onItemChange = { inputApplicationStartDate ->
                        applicationStartDateForShow.value = inputApplicationStartDate
                    },
                )

                LectureDetailSettingInputTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "○○시 ○○분",
                    onItemChange = { inputApplicationStartTime ->
                        applicationStartTimeForShow.value = inputApplicationStartTime
                    },
                )

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.application_deadline_date),
                )

                LectureDetailSettingInputTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "예시: ○○○○년 ○○월 ○○일",
                    onItemChange = { inputApplicationEndDate ->
                        applicationEndDateForShow.value = inputApplicationEndDate
                    },
                )

                LectureDetailSettingInputTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "○○시 ○○분",
                    onItemChange = { inputApplicationEndTime ->
                        applicationEndTimeForShow.value = inputApplicationEndTime
                    },
                )

                Spacer(modifier = modifier.height(24.dp))
            }
            item {
                BitgoeulSubjectText(
                    subjectText = stringResource(id = R.string.lecture_attendance_date),
                )

                LectureDetailSettingLectureDatesTextField(
                    modifier = modifier.fillMaxWidth(),
                    selectedItem = "수강일 입력(필수)",
                    onLectureDatesChanged = { completeDate, startTIme, endTime ->

                    }
                )

                Spacer(modifier = modifier.height(8.dp))
            }


            itemsIndexed(lectureDates) { index, lectureDatesItem ->
                Row {
                    LectureDetailSettingLectureDatesTextField(
                        modifier = modifier.fillMaxWidth(),
                        selectedItem = "수강일 입력(선택)",
                        onLectureDatesChanged = { completeDate, startTIme, endTime ->
                            onLectureDatesChanged(completeDate, startTIme, endTime)
                        }
                    )

                    if (index > 0) {
                        Spacer(modifier = modifier.width(12.dp))

                        DeleteIcon(
                            modifier = modifier.clickable {
                                onLectureDatesRemoveClicked()
                            }
                        )
                    }
                }

                Spacer(modifier = modifier.height(8.dp))
            }

            item {
                AddLectureDatesButton(
                    modifier = modifier.fillMaxWidth()
                ) {
                    onLectureDatesAddClicked()
                }

                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                BitgoeulSubjectText(subjectText = "수강 인원")

                LectureDetailSettingInputTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "수강 인원 입력 (5~10명)",
                    onItemChange = { inputMaxRegisteredUser ->
                        maxRegisteredUser.value = inputMaxRegisteredUser.toInt()
                    },
                )

                Spacer(modifier = modifier.height(200.dp))
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
    }
}