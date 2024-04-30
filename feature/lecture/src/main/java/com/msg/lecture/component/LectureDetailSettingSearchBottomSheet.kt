package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    onQuit: () -> Unit
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