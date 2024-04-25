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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.msg.design_system.component.picker.Picker
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureDetailSettingSearchDialog
import com.msg.lecture.component.LectureSettingTag
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
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
        onSearchResultItemCLick = { userId ->
            viewModel.userId.value = userId
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
    onApplyClicked: (LectureType, Semester, Division, String, String, UUID, Int, LocalDateTime?, LocalDateTime?, Int) -> Unit,
    onSearchProfessorClicked: (String) -> Unit,
    onSearchLineClicked: (String, Division) -> Unit,
    onSearchDepartmentClicked: (String) -> Unit,
    onSearchResultItemCLick: (UUID) -> Unit,
    onCompleteDateChanged: (completeDate: LocalDate) -> Unit,
    onStartTimeChanged: (startTIme: LocalTime) -> Unit,
    onEndTimeChanged: (endTime: LocalTime) -> Unit,
    lectureDates: MutableList<LectureDates>,
    startDateForConversion: LocalDate?,
    endDateForConversion: LocalDate?,
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
    val isSearchDialogVisible = remember { mutableStateOf(false) }
    val isClickedPickerType = remember { mutableStateOf("") }
    val isLectureCategoryTagSelected = remember { mutableStateOf("0") }
    val isLectureSemesterAttendedTagSelected = remember { mutableStateOf("0") }
    val isLectureDivisionTagSelected = remember { mutableStateOf("0") }
    val isLectureCreditTagSelected = remember { mutableStateOf("0") }
    val isShowDeleteIcon = remember { mutableStateOf(false) }

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

    val applicationStartTimeForShow = remember { mutableStateOf(savedStartTime?.toLocalTimeFormat() ?: "") }
    val applicationEndTimeForShow = remember { mutableStateOf(savedEndTime?.toLocalTimeFormat() ?: "") }
    val applicationStartDateForShow = remember { mutableStateOf(savedStartDate?.toKoreanFormat() ?: "") }
    val applicationEndDateForShow = remember { mutableStateOf(savedEndDate?.toKoreanFormat() ?: "") }

    val lectureAttendCompleteDateListForShow = remember { mutableStateListOf(savedCompleteDate?.toKoreanFormat() ?: "") }
    val lectureAttendStartTimeListForShow = remember { mutableStateListOf(savedStartTime?.toLocalTimeFormat() ?: "") }
    val lectureAttendEndTimeListForShow = remember { mutableStateListOf(savedEndTime?.toLocalTimeFormat() ?: "") }

    // TODO : 이거 스크린 내부 내용이 너무 많아서 컴포넌트화 해도 좀 더럽고 헷갈리는데 최대한 고쳐야함 ex. StackKnowledge V2 처럼 뭉탱이?로 컴포넌트화 해야하나?
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
                Text(
                    text = stringResource(id = R.string.lecture_category),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))

            }

            item {
                Text(
                    text = stringResource(id = R.string.semester_attended),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.lecture_division),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))

            }

            item {
                Text(
                    text = stringResource(id = R.string.credit),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))

            }

            item {
                Text(
                    text = stringResource(id = R.string.lecture_series),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))

            }

            item {
                Text(
                    text = stringResource(id = R.string.department),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )

                Picker(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = department.value.ifEmpty { stringResource(id = R.string.select_department) },
                    onClick = {
                        isSearchDialogVisible.value = !isSearchDialogVisible.value
                        isClickedPickerType.value = "학과"
                    }
                )

                Spacer(modifier = modifier.height(28.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.professor_in_charge),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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

                Spacer(modifier = modifier.height(28.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.application_start_date),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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
                        onItemChange = { startDateText ->
                            if (applicationStartDateForShow.value != startDateText) applicationStartDateForShow.value =
                                startDateText else applicationStartDateForShow.value = ""
                        },
                        isDatePicker = true,
                        onDatePickerQuit = { selectedStartDate ->
                            if (selectedStartDate != null) {
                                applicationStartDateForShow.value = selectedStartDate.toKoreanFormat()
                                startDateForConversion.value = selectedStartDate
                            }
                        }
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationStartTimeForShow.value.ifEmpty { stringResource(id = R.string.start_time) },
                        list = listOf(),
                        selectedItem = applicationStartTimeForShow.value,
                        onItemChange = { startTimeText ->
                            if (applicationStartTimeForShow.value != startTimeText) applicationStartTimeForShow.value =
                                startTimeText else applicationStartTimeForShow.value = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedStartTime ->
                            if (selectedStartTime != null) {
                                applicationStartTimeForShow.value = selectedStartTime.toLocalTimeFormat()
                                startTime.value = selectedStartTime
                            }
                        },
                    )
                }

                Spacer(modifier = modifier.height(28.dp))

                if (startDateForConversion.value != null && startTime.value != null) {
                    startDate.value = LocalDateTime.of(startDateForConversion.value, startTime.value)
                    startDateForConversion.value = null
                    startTime.value = null
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.application_deadline_date),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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
                        onItemChange = { endDateText ->
                            if (applicationEndDateForShow.value != endDateText) applicationEndDateForShow.value =
                                endDateText else applicationEndDateForShow.value = ""
                        },
                        isDatePicker = true,
                        onDatePickerQuit = { selectedEndDate ->
                            if (selectedEndDate != null) {
                                applicationEndDateForShow.value = selectedEndDate.toKoreanFormat()
                                endDateForConversion.value = selectedEndDate
                            }
                        }
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = applicationEndTimeForShow.value.ifEmpty { stringResource(id = R.string.deadeline_time) },
                        list = listOf(),
                        selectedItem = applicationEndTimeForShow.value,
                        onItemChange = { endTimeText ->
                            if (applicationEndTimeForShow.value != endTimeText) applicationEndTimeForShow.value =
                                endTimeText else applicationEndTimeForShow.value = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedEndTime ->
                            if (selectedEndTime != null) {
                                applicationEndTimeForShow.value = selectedEndTime.toLocalTimeFormat()
                                endTime.value = selectedEndTime
                            }
                        },
                    )
                }

                Spacer(modifier = modifier.height(28.dp))

                if (endDateForConversion.value != null && endTime.value != null) {
                    endDate.value = LocalDateTime.of(endDateForConversion.value, endTime.value)
                    endDateForConversion.value = null
                    endTime.value = null
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.lecture_attendance_date),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )
            }

            items(lectureDates.size + 1) { index ->
                if (index > 0) {
                    completeDatesComponentWeight.value = 0.85f
                    isShowDeleteIcon.value = true
                    Spacer(modifier = modifier.height(20.dp))
                } else {
                    completeDatesComponentWeight.value = 1f
                    isShowDeleteIcon.value = false
                }

                Row {
                    PickerTextField(
                        modifier = modifier.weight(completeDatesComponentWeight.value),
                        text = lectureAttendCompleteDateListForShow[index].ifEmpty {
                            stringResource(id = R.string.lecture_attendance_date)
                        },
                        list = listOf(),
                        selectedItem = lectureAttendCompleteDateListForShow[index],
                        onItemChange = { completeDateText ->
                            if (lectureAttendCompleteDateListForShow[index] != completeDateText) lectureAttendCompleteDateListForShow[index] =
                                completeDateText else lectureAttendCompleteDateListForShow[index] = ""
                        },
                        isDatePicker = true,
                        onDatePickerQuit = { selectedCompleteDate ->
                            if (selectedCompleteDate != null) {
                                lectureAttendCompleteDateListForShow[index] = selectedCompleteDate.toKoreanFormat()
                                onCompleteDateChanged(selectedCompleteDate)
                                lectureAttendCompleteDateListForShow.add("")
                            }
                        }
                    )

                    if (isShowDeleteIcon.value) {
                        DeleteIcon(
                            modifier = modifier
                                .weight(0.15f)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    onLectureDatesRemoveClicked()
                                    lectureAttendCompleteDateListForShow.removeAt(index)
                                    lectureAttendStartTimeListForShow.removeAt(index)
                                    lectureAttendEndTimeListForShow.removeAt(index)
                                },
                        )
                    }
                }

                Spacer(modifier = modifier.height(12.dp))

                Row {
                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = lectureAttendStartTimeListForShow[index].ifEmpty { stringResource(id = R.string.start_time) },
                        list = listOf(),
                        selectedItem = lectureAttendStartTimeListForShow[index],
                        onItemChange = { startTimeText ->
                            if (lectureAttendStartTimeListForShow[index] != startTimeText) lectureAttendStartTimeListForShow[index] =
                                startTimeText else lectureAttendStartTimeListForShow[index] = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedStartTime ->
                            if (selectedStartTime != null) {
                                lectureAttendStartTimeListForShow[index] = selectedStartTime.toLocalTimeFormat()
                                onStartTimeChanged(selectedStartTime)
                                lectureAttendStartTimeListForShow.add("")
                            }
                        },
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    PickerTextField(
                        modifier = modifier.weight(1f),
                        text = lectureAttendEndTimeListForShow[index].ifEmpty { stringResource(id = R.string.end_time) },
                        list = listOf(),
                        selectedItem = lectureAttendEndTimeListForShow[index],
                        onItemChange = { endTimeText ->
                            if (lectureAttendEndTimeListForShow[index] != endTimeText) lectureAttendEndTimeListForShow[index] =
                                endTimeText else lectureAttendEndTimeListForShow[index] = ""
                        },
                        isTimePicker = true,
                        onTimePickerQuit = { selectedEndTime ->
                            if (selectedEndTime != null) {
                                lectureAttendEndTimeListForShow[index] = selectedEndTime.toLocalTimeFormat()
                                onEndTimeChanged(selectedEndTime)
                                lectureAttendEndTimeListForShow.add("")
                            }
                        },
                    )
                }
            }

            item {
                Spacer(modifier = modifier.height(12.dp))

                Text(
                    modifier = modifier.clickable {
                        if (lectureDates.isNullOrEmpty()) {
                            lectureAttendCompleteDateListForShow.add("")
                            lectureAttendStartTimeListForShow.add("")
                            lectureAttendEndTimeListForShow.add("")
                        }
                        onLectureDatesAddClicked()
                    },
                    text = stringResource(id = R.string.add_date),
                    color = colors.G2,
                    style = typography.headlineMedium
                )

                Spacer(modifier = modifier.height(28.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.maximum_number_students),
                    color = colors.BLACK,
                    style = typography.bodyLarge,
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
                    Log.e("startDate", startDate.toString())
                    Log.e("endDate", endDate.toString())
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
            onProfessorListClick = { selectedProfessorUUID ->
                userId.value = selectedProfessorUUID
            },
            onCloseButtonClick = { isSearchDialogVisible.value = false },
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
        )
    }
}
