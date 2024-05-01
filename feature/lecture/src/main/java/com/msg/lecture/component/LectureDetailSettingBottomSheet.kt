package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.textfield.TrailingIconTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LectureDetailSettingSearchBottomSheet(
    searchProfessorData: SearchProfessorResponse,
    searchLineData: SearchLineResponse,
    searchDepartmentData: SearchDepartmentResponse,
    modifier: Modifier = Modifier,
    searchPlaceHolder: String,
    isVisible: Boolean,
    division: String,
    searchAPIType: String,
    onSearchButtonClick: (String, String) -> Unit,
    onProfessorListClick: (UUID) -> Unit,
    onDepartmentListClick: (String) -> Unit,
    onLineListClick: (String) -> Unit,
    onQuit: () -> Unit,
) {
    val keywordState = remember { mutableStateOf("") }

    if (isVisible) {
        BitgoeulAndroidTheme { colors, _ ->
            ModalBottomSheet(onDismissRequest = {
                onQuit()
            }) {
                Box(
                    modifier = modifier
                        .wrapContentSize()
                        .background(color = colors.WHITE)
                        .padding(horizontal = 28.dp, vertical = 24.dp)
                ) {
                    TrailingIconTextField(
                        modifier = modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth(),
                        placeholder = searchPlaceHolder,
                        value = keywordState.value,
                        onValueChange = { keyword ->
                            keywordState.value = keyword
                        },
                        isDisabled = false,
                        onClickButton = {
                            onSearchButtonClick(keywordState.value, division)
                        }
                    )

                    Spacer(modifier = modifier.height(8.dp))

                    when (searchAPIType) {
                        "강의 계열" -> {
                            LectureLineList(
                                modifier = modifier,
                                onClick = { selectedLineData ->
                                    onLineListClick(selectedLineData)
                                },
                                searchLineData = searchLineData,
                                division = division,
                                keyword = keywordState.value
                            )
                        }

                        "담당 교수" -> {
                            LectureProfessorList(
                                modifier = modifier,
                                onClick = { professor ->
                                    onProfessorListClick(professor)
                                },
                                searchProfessorData = searchProfessorData,
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
    isVisible: Boolean,
    onQuit: (String, String, String) -> Unit,
) {
    val completeDates = remember { mutableStateOf("") }
    val startTime = remember { mutableStateOf("") }
    val endTime = remember { mutableStateOf("") }

    if (isVisible) {
        BitgoeulAndroidTheme { colors, typography ->
            ModalBottomSheet(
                onDismissRequest = {
                    if (completeDates.value.isNotEmpty() && startTime.value.isNotEmpty() && endTime.value.isNotEmpty()) {
                        onQuit(completeDates.value, startTime.value, endTime.value)
                    }
                }) {
                Box(
                    modifier = modifier
                        .wrapContentSize()
                        .background(color = colors.WHITE)
                        .padding(horizontal = 28.dp, vertical = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "강의 수강일", color = colors.BLACK, style = typography.labelLarge)

                        CloseIcon(
                            modifier.clickable {

                            }
                        )
                    }
                    Spacer(modifier = modifier.height(24.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "강의 수강일을 입력해주세요 | ○○○○년 ○○월 ○○일",
                        onItemChange = { inputCompleteDates ->
                            completeDates.value = inputCompleteDates
                        },
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "강의 시작 시간을 입력해주세요 | ○○시 ○○분",
                        onItemChange = { inputStartTime ->
                            startTime.value = inputStartTime
                        },
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureDetailSettingInputTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "강의 종료 시간을 입력해주세요 | ○○시 ○○분",
                        onItemChange = { inputEndTime ->
                            endTime.value = inputEndTime
                        },
                    )

                    Spacer(modifier = modifier.height(152.dp))

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