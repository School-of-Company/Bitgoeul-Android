package com.msg.lecture

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.icon.DeleteIcon
import com.msg.design_system.component.text.BitgoeulSubjectText
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.LectureDetailSettingTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.AddLectureDatesButton
import com.msg.lecture.component.LectureDetailSettingInputTextField
import com.msg.lecture.component.LectureDetailSettingLectureDatesTextField
import com.msg.lecture.component.LectureDetailSettingSearchTextField
import com.msg.lecture.component.LectureSettingTag
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.entity.lecture.Instructors
import com.msg.model.entity.lecture.SearchDepartmentEntity
import com.msg.model.entity.lecture.SearchDivisionEntity
import com.msg.model.entity.lecture.SearchLineEntity
import com.msg.model.entity.lecture.SearchProfessorEntity
import com.msg.model.enumdata.Authority
import com.msg.ui.util.toKoreanFormat
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
    val focusManager = LocalFocusManager.current

    LectureDetailSettingScreen(
        focusManager = focusManager,
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
            viewModel.startDate.value = startDate
            viewModel.endDate.value = endDate
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
        onSearchDivisionClicked = { keyword ->
            viewModel.searchDivision(keyword = keyword)
            coroutineScope.launch {
                getDivisionSearchData(viewModel = viewModel, onDivisionSuccess = { data ->
                    viewModel.searchDivisionData.value = data
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
            viewModel.addLectureDates()
        },
        searchLineData = viewModel.searchLineData.value,
        searchProfessorData = viewModel.searchProfessorData.value,
        searchDepartmentData = viewModel.searchDepartmentData.value,
        searchDivisionData = viewModel.searchDivisionData.value,
        savedCreditPoint = viewModel.credit.value,
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


private suspend fun getLineSearchData(
    viewModel: LectureViewModel,
    onLineSuccess: (data: SearchLineEntity) -> Unit,
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

private suspend fun getProfessorSearchData(
    viewModel: LectureViewModel,
    onSearchProfessorSuccess: (data: SearchProfessorEntity) -> Unit,
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

private suspend fun getDepartmentSearchData(
    viewModel: LectureViewModel,
    onDepartmentSuccess: (data: SearchDepartmentEntity) -> Unit,
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

suspend fun getDivisionSearchData(
    viewModel: LectureViewModel,
    onDivisionSuccess: (data: SearchDivisionEntity) -> Unit,
) {
    viewModel.searchDivisionResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onDivisionSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
internal fun LectureDetailSettingScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    searchProfessorData: SearchProfessorEntity,
    searchLineData: SearchLineEntity,
    searchDepartmentData: SearchDepartmentEntity,
    searchDivisionData: SearchDivisionEntity,
    onCloseClicked: () -> Unit,
    onLectureDatesAddClicked: () -> Unit,
    onLectureDatesRemoveClicked: () -> Unit,
    onApplyClicked: (lectureType: String, semester: String, division: String, department: String, line: String, userId: UUID, credit: Int, startDate: LocalDateTime?, endDate: LocalDateTime?, maxRegisteredUser: Int) -> Unit,
    onSearchProfessorClicked: (String) -> Unit,
    onSearchLineClicked: (String, String) -> Unit,
    onSearchDepartmentClicked: (String) -> Unit,
    onSearchDivisionClicked: (String) -> Unit,
    onLectureDatesChanged: (completeDate: LocalDate, startTIme: LocalTime, endTime: LocalTime) -> Unit,
    savedLectureType: String,
    savedSemester: String,
    savedDivision: String,
    savedDepartment: String,
    savedLine: String,
    savedUserId: UUID,
    savedCreditPoint: Int,
    savedStartDate: LocalDateTime?,
    savedEndDate: LocalDateTime?,
    savedMaxRegisteredUser: Int,
) {
    val semesterList = listOf("1학년 2학기", "2학년 1학기", "2학년 2학기", "3학년 1학기")
    val lectureTypeList = listOf("상호학점인정교육과정", "유관기관프로그램", "대학탐방프로그램", "기업산학연계직업체험프로그램")

    val isRequiredCourse = remember { mutableStateOf("0") }

    val lectureType = remember { mutableStateOf(savedLectureType) }
    val division = remember { mutableStateOf(savedDivision) }
    val department = remember { mutableStateOf(savedDepartment) }
    val line = remember { mutableStateOf(savedLine) }
    val userId = remember { mutableStateOf(savedUserId) }
    val credit = remember { mutableIntStateOf(savedCreditPoint) }
    val semester = remember { mutableStateOf(savedSemester) }
    val startDate = remember { mutableStateOf(savedStartDate) }
    val endDate = remember { mutableStateOf(savedEndDate) }
    val maxRegisteredUser = remember { mutableIntStateOf(savedMaxRegisteredUser) }

    val applicationStartTimeForShow = remember { mutableStateOf("") }
    val applicationEndTimeForShow = remember { mutableStateOf("") }
    val applicationStartDateForShow = remember { mutableStateOf("") }
    val applicationEndDateForShow = remember { mutableStateOf("") }

    val lectureLineForShow = remember { mutableStateOf("") }
    val lectureDepartmentForShow = remember { mutableStateOf("") }
    val lectureTeacherInChargeForShow = remember { mutableStateOf("") }
    val lectureDivisionForShow = remember { mutableStateOf("") }
    val lectureDatesForShow = remember { mutableStateListOf("") }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        BitgoeulAndroidTheme { colors, typography ->
            LazyColumn(
                modifier = modifier
                    .wrapContentSize()
                    .background(color = colors.WHITE)
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    }
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
                        subjectText = "필수 수강 여부",
                    )

                    Row {
                        LectureSettingTag(
                            text = "필수",
                            isSelected = isRequiredCourse.value == "0",
                            onClicked = {
                                isRequiredCourse.value = "0"
                            },
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            text = "선택",
                            isSelected = isRequiredCourse.value == "1",
                            onClicked = {
                                isRequiredCourse.value = "1"
                            },
                        )
                    }

                    Spacer(modifier = modifier.height(24.dp))
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
                                selectedLectureType
                        },
                        isLectureType = true
                    )

                    if (lectureType.value == "기타") {
                        Spacer(modifier = modifier.height(8.dp))

                        DefaultTextField(
                            modifier = modifier.fillMaxSize(),
                            placeholder = "",
                            onValueChange = { inputString ->
                                lectureType.value = inputString
                            },
                            errorText = "",
                            isDisabled = false,
                            isError = false,
                            isLinked = false,
                            isReverseTrailingIcon = false,
                            onButtonClicked = {}
                        )
                    }
                    Spacer(modifier = modifier.height(24.dp))
                }

                item {
                    BitgoeulSubjectText(
                        subjectText = stringResource(id = R.string.lecture_division),
                    )

                    LectureDetailSettingSearchTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = lectureDivisionForShow.value.ifEmpty { "구분 선택" },
                        division = division.value,
                        onDivisionItemClicked = { selectedDivisionData ->
                            division.value = selectedDivisionData
                            lectureDivisionForShow.value = selectedDivisionData
                        },
                        isClickedPickerType = "구분",
                        onSearchDivisionClicked = { keyword ->
                            onSearchDivisionClicked(keyword)
                        },
                        searchLineData = searchLineData,
                        searchDepartmentData = searchDepartmentData,
                        searchProfessorData = searchProfessorData,
                        searchDivisionData = searchDivisionData
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
                        onLineItemClicked = { selectedLineData ->
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
                        searchDivisionData = searchDivisionData
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
                        onDepartmentItemClicked = { selectedDepartmentData ->
                            department.value = selectedDepartmentData
                            lectureDepartmentForShow.value = selectedDepartmentData
                        },
                        onSearchDepartmentClicked = { keyword ->
                            onSearchDepartmentClicked(keyword)
                        },
                        searchLineData = searchLineData,
                        searchDepartmentData = searchDepartmentData,
                        searchProfessorData = searchProfessorData,
                        searchDivisionData = searchDivisionData
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
                        onProfessorItemClicked = { selectedProfessorUUID, selectedProfessorName ->
                            userId.value = selectedProfessorUUID
                            lectureTeacherInChargeForShow.value = selectedProfessorName
                        },
                        onSearchProfessorClicked = { keyword ->
                            onSearchProfessorClicked(keyword)
                        },
                        searchLineData = searchLineData,
                        searchDepartmentData = searchDepartmentData,
                        searchProfessorData = searchProfessorData,
                        searchDivisionData = searchDivisionData
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
                        selectedItem = lectureDatesForShow[0].ifEmpty { "수강일 입력(필수)" },
                        onLectureDatesChanged = { completeDates, startTime, endTime ->
                            onLectureDatesChanged(completeDates, startTime, endTime)
                            Log.e("0 index completeDates", completeDates.toString())
                            Log.e("0 index startTime", startTime.toString())
                            Log.e("0 index endTime", endTime.toString())
                            lectureDatesForShow[0] =
                                completeDates.toKoreanFormat() + " " + startTime.toKoreanFormat() + " ~ " + endTime.toKoreanFormat()
                        }
                    )

                    Spacer(modifier = modifier.height(8.dp))
                }

                itemsIndexed(lectureDatesForShow) { index, _ ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LectureDetailSettingLectureDatesTextField(
                            modifier = modifier.weight(0.9f),
                            selectedItem = lectureDatesForShow[index].ifEmpty { "수강일 입력(선택)" },
                            onLectureDatesChanged = { completeDates, startTime, endTime ->
                                onLectureDatesChanged(completeDates, startTime, endTime)
                                lectureDatesForShow.getOrNull(index)?.let {
                                    lectureDatesForShow[index] =
                                        completeDates.toKoreanFormat() + " " + startTime.toKoreanFormat() + " ~ " + endTime.toKoreanFormat()
                                }
                            }
                        )

                        Spacer(modifier = modifier.width(12.dp))

                        DeleteIcon(
                            modifier = modifier
                                .clickable {
                                    onLectureDatesRemoveClicked()
                                    lectureDatesForShow.forEachIndexed { index, _ ->
                                        lectureDatesForShow.removeAt(
                                            index
                                        )
                                    }
                                }
                        )
                    }

                    Spacer(modifier = modifier.height(8.dp))
                }

                item {
                    AddLectureDatesButton(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        lectureDatesForShow.add("")
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
}

@Preview
@Composable
private fun LectureDetailSettingScreenPre() {
    LectureDetailSettingScreen(
        onCloseClicked = {},
        onApplyClicked = { _, _, _, _, _, _, _, _, _, _ -> },
        onSearchProfessorClicked = {},
        onSearchLineClicked = { _, _ -> },
        onSearchDepartmentClicked = {},
        onSearchDivisionClicked = {},
        onLectureDatesAddClicked = {},
        onLectureDatesRemoveClicked = {},
        onLectureDatesChanged = { _, _, _ -> },
        searchLineData = SearchLineEntity(
            lines = listOf("A계열", "B계열", "C계열")
        ),
        searchProfessorData = SearchProfessorEntity(
            instructors = listOf(
                Instructors(
                    id = UUID.randomUUID(),
                    name = "채종인",
                    organization = "광주소프트웨어마이스터고등학교",
                    authority = Authority.ROLE_PROFESSOR
                )
            )
        ),
        searchDepartmentData = SearchDepartmentEntity(
            departments = listOf("A학과", "B학과", "C학과")
        ),
        searchDivisionData = SearchDivisionEntity(
            divisions = listOf("A구분", "B구분", "C구분")
        ),
        savedLectureType = "lectureType",
        savedSemester = "semester",
        savedDivision = "division",
        savedDepartment = "department",
        savedLine = "line",
        savedUserId = UUID.randomUUID(),
        savedCreditPoint = 3,
        savedStartDate = LocalDateTime.now(),
        savedEndDate = LocalDateTime.now(),
        savedMaxRegisteredUser = 10,
        focusManager = LocalFocusManager.current
    )
}