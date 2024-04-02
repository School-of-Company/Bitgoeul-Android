package com.msg.lecture

import android.util.Log
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
import com.msg.design_system.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.picker.Picker
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureAttendanceDatePicker
import com.msg.lecture.component.LectureDetailSettingSearchDialog
import com.msg.lecture.component.LectureSettingTag
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
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
fun LectureDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    LectureDetailSettingScreen(
        onCloseClicked = onCloseClicked,
        onApplyClicked = { lectureType, semester, division, department, line, userId, credit, startDate, endDate, completeDate, endTime, startTime, maxRegisteredUser ->
            viewModel.lectureType.value = lectureType
            viewModel.semester.value = semester
            viewModel.division.value = division
            viewModel.department.value = department
            viewModel.line.value = line
            viewModel.userId.value = userId
            viewModel.credit.value = credit
            viewModel.completeDate.value = completeDate
            viewModel.startTime.value = startTime
            viewModel.endTime.value = endTime
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
                    Log.e("onSearchProfessorClicked coroutineScope", data.toString())
                })
            }
        },
        onSearchLineClicked = { keyword, division ->
            viewModel.searchLine(keyword = keyword, division = division)
            coroutineScope.launch {
                getLineSearchData(viewModel = viewModel, onLineSuccess = { data ->
                    viewModel.searchLineData.value = data
                    Log.e("onSearchLineClicked coroutineScope", data.toString())
                })
            }
        },
        onSearchDepartmentClicked = { keyword ->
            viewModel.searchDepartment(keyword = keyword)
            coroutineScope.launch {
                getDepartmentSearchData(viewModel = viewModel, onDepartmentSuccess = { data ->
                    viewModel.searchDepartmentData.value = data
                    Log.e("onSearchDepartmentClicked coroutineScope", data.toString())
                })
            }

        },
        onSearchResultItemCLick = { userId ->
            viewModel.userId.value = userId
            Log.e("userId", userId.toString())
        },
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
                Log.e("계열 데이터", response.toString())
            }

            else -> {
                Log.e("계열 데이터 안불러와짐", response.toString())
            }
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
                Log.e("getProfessorData 함수에서의 교수 데이터", response.toString())
            }

            else -> {
                Log.e("getProfessorData 함수에서의 교수 데이터 안불러와짐", response.toString())
            }
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
                Log.e("학과 데이터", response.toString())
            }

            else -> {
                Log.e("학과 데이터 안불러와짐", response.toString())
            }
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
    onApplyClicked: (LectureType, Semester, Division, String, String, UUID, Int, LocalDateTime?, LocalDateTime?, LocalDate?, LocalTime?, LocalTime?, Int) -> Unit,
    onSearchProfessorClicked: (String) -> Unit,
    onSearchLineClicked: (String, Division) -> Unit,
    onSearchDepartmentClicked: (String) -> Unit,
    onSearchResultItemCLick: (UUID) -> Unit,
    savedLectureType: LectureType,
    savedSemester: Semester,
    savedDivision: Division,
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
    val scrollState = rememberScrollState()
    val isTimeBottomSheetVisible = remember { mutableStateOf(false) }
    val isSearchDialogVisible = remember { mutableStateOf(false) }
    val isClickedPickerType = remember { mutableStateOf("") }
    val isLectureCategoryTagSelected = remember { mutableStateOf("0") }
    val isLectureSemesterAttendedTagSelected = remember { mutableStateOf("0") }
    val isLectureDivisionTagSelected = remember { mutableStateOf("0") }
    val isLectureCreditTagSelected = remember { mutableStateOf("0") }

    val lectureType = remember { mutableStateOf(savedLectureType) }
    val division = remember { mutableStateOf(savedDivision) }
    val department = remember { mutableStateOf(savedDepartment) }
    val line = remember { mutableStateOf(savedLine) }
    val userId = remember { mutableStateOf(savedUserId) }
    val credit = remember { mutableIntStateOf(savedCreditPoint) }
    val semester = remember { mutableStateOf(savedSemester) }
    val startTime = remember { mutableStateOf(savedStartTime) }
    val startDate = remember { mutableStateOf(savedStartDate) }
    val endTime = remember { mutableStateOf(savedEndTime) }
    val endDate = remember { mutableStateOf(savedEndDate) }
    val completeDate = remember { mutableStateOf(savedCompleteDate) }
    val maxRegisteredUser = remember { mutableIntStateOf(savedMaxRegisteredUser) }

    val selectedStartLocalDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedStartLocalTime = remember { mutableStateOf(LocalTime.now()) }
    val selectedEndLocalDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedEndLocalTime = remember { mutableStateOf(LocalTime.now()) }

    val applicationStartTimeForShow = remember { mutableStateOf(savedStartTime?.toLocalTimeFormat() ?: "") }
    val applicationEndTimeForShow = remember { mutableStateOf(savedEndTime?.toLocalTimeFormat() ?: "") }
    val applicationStartDateForShow = remember { mutableStateOf(savedStartDate?.toKoreanFormat() ?: "") }
    val applicationEndDateForShow = remember { mutableStateOf(savedEndDate?.toKoreanFormat() ?: "") }
    val lectureAttendCompleteDateForShow = remember { mutableStateOf(savedCompleteDate?.toKoreanFormat() ?: "") }
    val lectureAttendStartTimeDateForShow = remember { mutableStateOf(savedEndTime?.toLocalTimeFormat() ?: "") }
    val lectureAttendEndTimeDateForShow = remember { mutableStateOf(savedEndTime?.toLocalTimeFormat() ?: "") }

    // TODO : 이거 스크린 내부 내용이 너무 많아서 컴포넌트화 해도 좀 더럽고 헷갈리는데 최대한 고쳐야함 ex. StackKnowledge V2 처럼 뭉탱이?로 컴포넌트화 해야하나?
    BitgoeulAndroidTheme { colors, type ->
        Column(
            modifier = modifier
                .background(color = colors.WHITE)
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.lecture_detail_setting),
                    color = colors.BLACK,
                    style = type.titleSmall,
                )

                CloseIcon(
                    modifier = modifier.clickable {
                        onCloseClicked()
                    }
                )

            }

            Spacer(modifier = modifier.height(18.dp))

            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .wrapContentSize()
            ) {
                Text(
                    text = stringResource(id = R.string.lecture_category),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                LectureSettingTag(
                    modifier = modifier,
                    text = stringResource(id = R.string.mutual_credit_recognition_curriculum),
                    isSelected = isLectureCategoryTagSelected.value == "0",
                    onClick = {
                        isLectureCategoryTagSelected.value = "0"
                        lectureType.value = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM
                    }
                )

                Spacer(modifier = modifier.height(16.dp))

                LectureSettingTag(
                    modifier = modifier,
                    text = stringResource(id = R.string.university_visit_program),
                    isSelected = isLectureCategoryTagSelected.value == "1",
                    onClick = {
                        isLectureCategoryTagSelected.value = "1"
                        lectureType.value = LectureType.UNIVERSITY_EXPLORATION_PROGRAM
                    }
                )

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.semester_attended),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Row {
                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.first_year_second_semester),
                        isSelected = isLectureSemesterAttendedTagSelected.value == "0",
                        onClick = {
                            isLectureSemesterAttendedTagSelected.value = "0"
                            semester.value = Semester.FIRST_YEAR_FALL_SEMESTER
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.second_year_first_semester),
                        isSelected = isLectureSemesterAttendedTagSelected.value == "1",
                        onClick = {
                            isLectureSemesterAttendedTagSelected.value = "1"
                            semester.value = Semester.SECOND_YEAR_SPRING_SEMESTER
                        }
                    )
                }

                Spacer(modifier = modifier.height(16.dp))

                Row {
                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.second_year_second_semester),
                        isSelected = isLectureSemesterAttendedTagSelected.value == "2",
                        onClick = {
                            isLectureSemesterAttendedTagSelected.value = "2"
                            semester.value = Semester.SECOND_YEAR_FALL_SEMESTER
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.third_year_first_semester),
                        isSelected = isLectureSemesterAttendedTagSelected.value == "3",
                        onClick = {
                            isLectureSemesterAttendedTagSelected.value = "3"
                            semester.value = Semester.THIRD_YEAR_SPRING_SEMESTER
                        }
                    )
                }
                Spacer(modifier = modifier.height(26.dp))

                Text(
                    text = stringResource(id = R.string.lecture_division),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Row {
                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.car_industry),
                        isSelected = isLectureDivisionTagSelected.value == "0",
                        onClick = {
                            isLectureDivisionTagSelected.value = "0"
                            division.value = Division.AUTOMOBILE_INDUSTRY
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.energy_industry),
                        isSelected = isLectureDivisionTagSelected.value == "1",
                        onClick = {
                            isLectureDivisionTagSelected.value = "1"
                            division.value = Division.ENERGY_INDUSTRY
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))
                }

                Spacer(modifier = modifier.height(16.dp))

                Row {
                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.medical_health),
                        isSelected = isLectureDivisionTagSelected.value == "2",
                        onClick = {
                            isLectureDivisionTagSelected.value = "2"
                            division.value = Division.MEDICAL_HEALTHCARE
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.ai_convergence_composite),
                        isSelected = isLectureDivisionTagSelected.value == "3",
                        onClick = {
                            isLectureDivisionTagSelected.value = "3"
                            division.value = Division.AI_CONVERGENCE
                        }
                    )
                }

                Spacer(modifier = modifier.height(16.dp))

                LectureSettingTag(
                    modifier = modifier,
                    text = stringResource(id = R.string.culture_industry),
                    isSelected = isLectureDivisionTagSelected.value == "4",
                    onClick = {
                        isLectureDivisionTagSelected.value = "4"
                        division.value = Division.CULTURAL_INDUSTRY
                    }
                )

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.credit),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.one_point),
                        isSelected = isLectureCreditTagSelected.value == "0",
                        onClick = {
                            isLectureCreditTagSelected.value = "0"
                            credit.value = 1
                        }
                    )

                    Spacer(modifier = modifier.width(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        text = stringResource(id = R.string.two_point),
                        isSelected = isLectureCreditTagSelected.value == "1",
                        onClick = {
                            isLectureCreditTagSelected.value = "1"
                            credit.value = 2
                        }
                    )
                }

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.lecture_series),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Picker(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = line.value.ifEmpty { stringResource(id = R.string.select_lecture) },
                    onClick = {
                        isSearchDialogVisible.value = !isSearchDialogVisible.value
                        isClickedPickerType.value = "강의 계열"
                    }
                )

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.department),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Picker(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.select_department),
                    onClick = {
                        isSearchDialogVisible.value = !isSearchDialogVisible.value
                        isClickedPickerType.value = "학과"
                    }
                )

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.professor_in_charge),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Picker(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.select_professor_in_charge),
                    onClick = {
                        isSearchDialogVisible.value = !isSearchDialogVisible.value
                        isClickedPickerType.value = "담당 교수"
                    }
                )

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.application_start_date),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationStartDateForShow.value.ifEmpty { stringResource(id = R.string.application_start_date) },
                        list = listOf(),
                        selectedItem = applicationStartDateForShow.value,
                        onItemChange = {
                            if (applicationStartDateForShow.value != it) applicationStartDateForShow.value =
                                it else applicationStartDateForShow.value = ""
                        },
                        isDatePicker = true,
                        onDatePickerQuit = { selectedStartDate ->
                            if (selectedStartDate != null) {
                                applicationStartDateForShow.value =
                                    selectedStartDate.toKoreanFormat()
                                selectedStartLocalDate.value = selectedStartDate
                            }
                        }
                    )

                    Spacer(modifier = modifier.width(8.dp))


                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationStartTimeForShow.value.ifEmpty { stringResource(id = R.string.start_time) },
                        list = listOf(),
                        selectedItem = applicationStartTimeForShow.value,
                        onItemChange = {
                            if (applicationStartTimeForShow.value != it) applicationStartTimeForShow.value =
                                it else applicationStartTimeForShow.value = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedStartTime ->
                            if (selectedStartTime != null) {
                                applicationStartTimeForShow.value =
                                    selectedStartTime.toLocalTimeFormat()
                                selectedStartLocalTime.value = selectedStartTime
                            }
                        },
                    )
                }

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.application_deadline_date),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationEndDateForShow.value.ifEmpty { stringResource(id = R.string.application_deadline_date) },
                        list = listOf(),
                        selectedItem = applicationEndDateForShow.value,
                        onItemChange = {
                            if (applicationEndDateForShow.value != it) applicationEndDateForShow.value =
                                it else applicationEndDateForShow.value = ""
                        },
                        isDatePicker = true,
                        onDatePickerQuit = { selectedEndDate ->
                            if (selectedEndDate != null) {
                                applicationEndDateForShow.value = selectedEndDate.toKoreanFormat()
                                selectedEndLocalDate.value = selectedEndDate
                            }
                        }
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationEndTimeForShow.value.ifEmpty { stringResource(id = R.string.deadeline_time) },
                        list = listOf(),
                        selectedItem = applicationEndTimeForShow.value,
                        onItemChange = {
                            if (applicationEndTimeForShow.value != it) applicationEndTimeForShow.value =
                                it else applicationEndTimeForShow.value = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedEndTime ->
                            if (selectedEndTime != null) {
                                applicationEndTimeForShow.value =
                                    selectedEndTime.toLocalTimeFormat()
                                selectedEndLocalTime.value = selectedEndTime
                            }
                        },
                    )
                }

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.lecture_attendance_date),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                LectureAttendanceDatePicker(
                    modifier = modifier,
                    lectureAttendanceDateText = lectureAttendCompleteDateForShow.value.ifEmpty {
                        stringResource(
                            id = R.string.lecture_attendance_date
                        )
                    },
                    lectureStartTimeText = lectureAttendStartTimeDateForShow.value.ifEmpty {
                        stringResource(
                            id = R.string.start_time
                        )
                    },
                    lectureEndTimeText = lectureAttendEndTimeDateForShow.value.ifEmpty {
                        stringResource(
                            id = R.string.end_time
                        )
                    },
                    onDatePickerQuit = { selectedCompleteDate ->
                        lectureAttendCompleteDateForShow.value =
                            selectedCompleteDate?.toKoreanFormat() ?: ""
                        completeDate.value = selectedCompleteDate
                    },
                    onStartTimePickerQuit = { checkedStartTime ->
                        lectureAttendStartTimeDateForShow.value =
                            checkedStartTime?.toLocalTimeFormat() ?: ""
                        startTime.value = checkedStartTime
                    },
                    onEndTimePickerQuit = { selectedEndTime ->
                        lectureAttendEndTimeDateForShow.value =
                            selectedEndTime?.toLocalTimeFormat() ?: ""
                        endTime.value = selectedEndTime
                    }
                )


                Text(
                    text = stringResource(id = R.string.maximum_number_students),
                    color = colors.BLACK,
                    style = type.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                DefaultTextField(
                    modifier = modifier
                        .padding(bottom = 128.dp)
                        .fillMaxWidth(),
                    placeholder = stringResource(id = R.string.enter_maximum_number_students),
                    errorText = "Incorrect Format", // 에러 텍스트는 임의의 값임
                    onValueChange = {},
                    onClickButton = {},
                    isNumberOnly = true,
                    isError = false,
                    isLinked = false,
                    isDisabled = false,
                    isReadOnly = false,
                    isReverseTrailingIcon = false,
                    onClick = {}
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
                        completeDate.value,
                        startTime.value,
                        endTime.value,
                        maxRegisteredUser.value
                    )
                }
            }
        }

        LectureDetailSettingSearchDialog(
            isVisible = isSearchDialogVisible.value,
            text = when (isClickedPickerType.value) {
                "강의 계열" -> stringResource(id = R.string.select_lecture)
                "학과" -> stringResource(id = R.string.select_department)
                "담당 교수" -> stringResource(id = R.string.select_professor_in_charge)
                else -> ""
            },
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
            onResultListClick = onSearchResultItemCLick,
            onCloseButtonClick = { isSearchDialogVisible.value = false },
            division = division.value,
            searchProfessorData = searchProfessorData,
            searchLineData = searchLineData,
            searchDepartmentData = searchDepartmentData,
            onDepartmentAndLineListClick = onSearchDepartmentClicked
        )
    }
}