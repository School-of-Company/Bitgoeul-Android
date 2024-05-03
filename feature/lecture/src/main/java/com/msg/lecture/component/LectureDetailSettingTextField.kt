package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.util.toLocalDate
import com.msg.lecture.util.toLocalTime
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID


@Composable
fun LectureDetailSettingInputTextField(
    modifier: Modifier,
    placeholder: String,
    onItemChange: (item: String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, _ ->
        Column(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(8.dp))

            DefaultTextField(
                modifier = modifier.fillMaxWidth(),
                placeholder = placeholder,
                errorText = "",
                isError = false,
                isDisabled = false,
                isLinked = false,
                isReverseTrailingIcon = false,
                onValueChange = { inputString ->
                    onItemChange(inputString)
                },
                onClickButton = {},
            )
        }
    }
}

@Composable
fun LectureDetailSettingSearchTextField(
    modifier: Modifier,
    placeholder: String,
    division: String = "",
    onSearchLineClicked: (String, String) -> Unit = { _, _ -> },
    onSearchDepartmentClicked: (String) -> Unit = {},
    onSearchProfessorClicked: (String) -> Unit = {},
    onProfessorItemClick: (UUID, String) -> Unit = { _, _ -> },
    onDepartmentItemClick: (String) -> Unit = {},
    onLineItemClick: (String) -> Unit = {},
    isClickedPickerType: String,
    searchProfessorData: SearchProfessorResponse,
    searchLineData: SearchLineResponse,
    searchDepartmentData: SearchDepartmentResponse,
) {
    val isFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isFocused.value) colors.P5 else colors.G1,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = colors.WHITE)
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isFocused.value = true
                },
        ) {
            Text(
                text = placeholder,
                style = typography.bodySmall,
                color = colors.G2
            )
        }
        if (isFocused.value) {
            LectureDetailSettingSearchBottomSheet(
                searchPlaceHolder = when (isClickedPickerType) {
                    "강의 계열" -> stringResource(id = R.string.lecture_series_placeholder)
                    "학과" -> stringResource(id = R.string.department_placeholder)
                    "담당 교수" -> stringResource(id = R.string.professor_in_charge_placeholder)
                    else -> ""
                },
                onSearchButtonClick = { keyword, division ->
                    when (isClickedPickerType) {
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
                searchAPIType = isClickedPickerType,
                onProfessorListClick = { selectedProfessorUUID, selectedProfessorName ->
                    onProfessorItemClick(selectedProfessorUUID, selectedProfessorName)
                },
                division = division,
                searchProfessorData = searchProfessorData,
                searchLineData = searchLineData,
                searchDepartmentData = searchDepartmentData,
                onDepartmentListClick = { selectedDepartmentData ->
                    onDepartmentItemClick(selectedDepartmentData)
                },
                onLineListClick = { selectedLineData ->
                    onLineItemClick(selectedLineData)
                },
                onQuit = {
                    isFocused.value = false
                }
            )
        }
    }
}

@Composable
fun LectureDetailSettingLectureDatesTextField(
    modifier: Modifier,
    selectedItem: String,
    onLectureDatesChanged: (completeDate: LocalDate, startTIme: LocalTime, endTime: LocalTime) -> Unit,
) {
    val isFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isFocused.value) colors.P5 else colors.G1,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = colors.WHITE)
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isFocused.value = true
                },
        ) {
            Text(
                text = selectedItem,
                style = typography.bodySmall,
                color = colors.G2
            )
        }
        if (isFocused.value) {
            LectureDetailSettingLectureDatesBottomSheet(
                onQuit = { completeDates, startTime, endTime ->
                    onLectureDatesChanged(
                        completeDates.toLocalDate(),
                        startTime.toLocalTime(),
                        endTime.toLocalTime()
                    )
                },
            )
        }
    }
}