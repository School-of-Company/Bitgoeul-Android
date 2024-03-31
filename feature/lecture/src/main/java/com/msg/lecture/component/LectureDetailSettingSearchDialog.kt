package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.R
import com.msg.design_system.component.icon.BlackCloseIcon
import com.msg.design_system.component.textfield.TrailingIconTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.model.lecture.SearchResponseModel
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.util.UUID

@Composable
fun LectureDetailSettingSearchDialog(
    searchProfessorData: SearchProfessorResponse,
    searchData: List<SearchResponseModel>,
    modifier: Modifier = Modifier,
    text: String,
    searchPlaceHolder: String,
    isVisible: Boolean,
    division: Division,
    searchAPIType: String,
    onSearchButtonClick: (String, Division) -> Unit,
    onResultListClick: (UUID) -> Unit,
    onDepartmentAndLineListClick: (String) -> Unit,
    onCloseButtonClick: () -> Unit
) {
    val keywordState = remember { mutableStateOf("") }

    if (isVisible) {
        BitgoeulAndroidTheme { colors, typography ->
            Dialog(onDismissRequest = {}) {
                Surface {
                    Box(
                        modifier = modifier
                            .background(color = colors.WHITE, RoundedCornerShape(8.dp))
                            .wrapContentHeight()
                    ) {
                        Spacer(
                            modifier = modifier
                                .padding(top = 150.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = colors.G9)
                        )
                        Column(
                            modifier = modifier.padding(horizontal = 24.dp)
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp, bottom = 24.dp)
                            ) {
                                Text(
                                    text = text,
                                    color = colors.BLACK,
                                    style = typography.labelSmall,
                                    modifier = modifier
                                        .weight(0.7f)
                                )
                                BlackCloseIcon(
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            onCloseButtonClick()
                                        }
                                )
                            }
                            TrailingIconTextField(
                                modifier = Modifier
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


                            Spacer(modifier = modifier.height(18.dp))

                            when (searchAPIType) {
                                "학과" -> {
                                    LectureDetailSettingDepartmentSearchList(
                                        modifier = modifier,
                                        onClick = onDepartmentAndLineListClick,
                                        data = searchData
                                    )
                                }

                                else -> {
                                    LectureDetailSettingSearchList(
                                        modifier = modifier,
                                        onClick = onResultListClick,
                                        searchProfessorData = searchProfessorData,
                                        searchAPIType = searchAPIType,
                                        searchData = searchData,
                                        division = division,
                                        keyword = keywordState.value
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun SelectProfessorDialogPre() {

}