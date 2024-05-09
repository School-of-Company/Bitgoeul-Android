package com.msg.lecture.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.textfield.TrailingIconTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchDivisionResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LectureDetailSettingSearchBottomSheet(
    searchProfessorData: SearchProfessorResponse,
    searchLineData: SearchLineResponse,
    searchDepartmentData: SearchDepartmentResponse,
    searchDivisionData: SearchDivisionResponse,
    modifier: Modifier = Modifier,
    searchPlaceHolder: String,
    division: String,
    searchAPIType: String,
    onSearchButtonClick: (keyword: String, division: String) -> Unit,
    onProfessorListClick: (professorUUID: UUID, professorName: String) -> Unit,
    onDepartmentListClick: (String) -> Unit,
    onLineListClick: (String) -> Unit,
    onDivisionListClick: (String) -> Unit,
    onQuit: () -> Unit,
) {
    val keywordState = remember { mutableStateOf("") }

    BitgoeulAndroidTheme { colors, _ ->
        ModalBottomSheet(
            onDismissRequest = {
                onQuit()
            },
            containerColor = colors.WHITE
        ) {
            LaunchedEffect(true) {
                onSearchButtonClick("", "")
            }
            Column(
                modifier = modifier
                    .wrapContentSize()
                    .background(color = colors.WHITE)
                    .padding(horizontal = 28.dp, vertical = 24.dp)
            ) {
                TrailingIconTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    placeholder = searchPlaceHolder,
                    value = keywordState.value,
                    onValueChange = { keyword ->
                        keywordState.value = keyword
                    },
                    isDisabled = false,
                    onClickButton = {
                        onSearchButtonClick(keywordState.value, division)
                    },
                )

                Spacer(modifier = modifier.height(8.dp))
                if (searchProfessorData.instructors.isNotEmpty() || searchDepartmentData.departments.isNotEmpty() || searchLineData.lines.isNotEmpty()) {
                    when (searchAPIType) {
                        "강의 계열" -> {
                            LectureLineList(
                                modifier = modifier,
                                onClick = { selectedLineData ->
                                    onLineListClick(selectedLineData)
                                },
                                data = searchLineData,
                                division = division,
                                keyword = keywordState.value
                            )
                        }

                        "담당 교수" -> {
                            LectureProfessorList(
                                modifier = modifier,
                                onClick = { professor, selectedProfessorName ->
                                    onProfessorListClick(professor, selectedProfessorName)
                                },
                                data = searchProfessorData,
                                division = division,
                                keyword = keywordState.value
                            )
                        }

                        "학과" -> {
                            LectureDepartmentList(
                                modifier = modifier,
                                onClick = { department ->
                                    onDepartmentListClick(department)
                                },
                                data = searchDepartmentData
                            )
                        }

                        "구분" -> {
                            LectureDivisionList(
                                modifier = modifier,
                                onClick = { division ->
                                    onDivisionListClick(division)
                                },
                                data = searchDivisionData
                            )
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LectureDetailSettingLectureDatesBottomSheet(
    modifier: Modifier = Modifier,
    onQuit: (completeDates: String, startTime: String, endTime: String) -> Unit,
    onDismissQuest: () -> Unit = {},
) {
    val completeDates = remember { mutableStateOf("") }
    val startTime = remember { mutableStateOf("") }
    val endTime = remember { mutableStateOf("") }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    BitgoeulAndroidTheme { colors, typography ->
        LaunchedEffect(key1 = Unit) {
            bottomSheetState.expand()
        }

        if (bottomSheetState.isVisible) {
            ModalBottomSheet(
                containerColor = colors.WHITE,
                sheetState = bottomSheetState,
                onDismissRequest = {
                    if (completeDates.value.isNotEmpty() && startTime.value.isNotEmpty() && endTime.value.isNotEmpty()) {
                        onQuit(completeDates.value, startTime.value, endTime.value)
                    } else {
                        onDismissQuest()
                        Log.e("dismiss request else on", "dismiss request else on")
                    }
                    Log.e("input completeDates", completeDates.toString())
                    Log.e("input startTime", startTime.toString())
                    Log.e("input endTime", endTime.toString())
                },
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = colors.WHITE)
                        .padding(horizontal = 28.dp, vertical = 24.dp)
                ) {
                    Row {
                        Text(
                            text = "강의 수강일",
                            color = colors.BLACK,
                            style = typography.labelLarge
                        )

                        Spacer(modifier = modifier.weight(1f))

                        CloseIcon(
                            modifier.clickable {
                                if (completeDates.value.isNotEmpty() && startTime.value.isNotEmpty() && endTime.value.isNotEmpty()) {
                                    onQuit(completeDates.value, startTime.value, endTime.value)
                                } else {
                                    coroutineScope.launch {
                                        bottomSheetState.hide()
                                    }
                                }
                            }
                        )
                    }
                    Spacer(modifier = modifier.height(24.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "예) 2024년 02월 04일",
                        onItemChange = { inputCompleteDates ->
                            completeDates.value = inputCompleteDates
                        },
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "예) 13시 50분 시작",
                        onItemChange = { inputStartTime ->
                            startTime.value = inputStartTime
                        },
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "예) 15시 30분 종료",
                        onItemChange = { inputEndTime ->
                            endTime.value = inputEndTime
                        },
                    )

                    Spacer(modifier = modifier.height(80.dp))

                    BitgoeulButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = {
                            if (completeDates.value.isNotEmpty() && startTime.value.isNotEmpty() && endTime.value.isNotEmpty()) {
                                onQuit(completeDates.value, startTime.value, endTime.value)
                            }
                        },
                        text = "적용하기",
                    )
                }
            }
        }
    }
}