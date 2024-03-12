package com.msg.lecture

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.bottomsheet.SelectedIndicator
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureTypeTag

@Composable
fun LectureDetailSettingScreen() {
    val selected = remember { mutableStateListOf("0", "0") }
    val lectureCategory = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = Modifier
                    .background(color = colors.WHITE)
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.lecture_detail_setting),
                        color = colors.BLACK,
                        style = type.titleSmall,
                    )

                    CloseIcon()

                }
                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.lecture_category),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Column {
                        LectureTypeTag(
                            modifier = Modifier,
                            lectureType = stringResource(id = R.string.mutual_credit_recognition_curriculum)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LectureTypeTag(
                            modifier = Modifier,
                            lectureType = stringResource(id = R.string.university_visit_program)
                        )
                        Spacer(modifier = Modifier.height(28.dp))

                    }

                    Text(
                        text = stringResource(id = R.string.application_start_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.application_start_date)
                        )

                        Spacer(modifier = Modifier.width(8.dp))


                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.start_time)
                        )

                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.application_deadline_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.application_deadline_date)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.deadeline_time)
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.start_semester_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.start_semester_date)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Picker(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.start_semester_time)
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))


                    if (lectureCategory.value == "mutual_credit_recognition_curriculum") {
                        Column {
                            Text(
                                text = stringResource(id = R.string.credits_awarded),
                                color = colors.BLACK,
                                style = type.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Picker(
                                    modifier = Modifier.weight(1f),
                                    text = stringResource(id = R.string.select_credits_awarded)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                            }
                            Spacer(modifier = Modifier.height(28.dp))

                        }
                    }

                    Text(
                        text = stringResource(id = R.string.maximum_number_students),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        DefaultTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = stringResource(id = R.string.enter_maximum_number_students),
                            errorText = "Incorrect", // 에러 텍스트는 임의의 값임
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

                    Spacer(modifier = Modifier.height(165.dp))
                }
            }


            BitgoeulButton(
                text = stringResource(id = R.string.apply),
                modifier = Modifier
                    .padding(bottom = 38.dp, top = 16.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 24.dp),
            ) {
            }
        }
    }
}

@Composable
fun Picker(
    modifier: Modifier = Modifier,
    text: String,
) {
    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable {

                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                color = colors.G2,
                style = type.bodySmall,
            )

            PickerArrowIcon(isSelected = false)
        }
    }
}

@Preview
@Composable
fun LectureDetailSettingScreenPre() {
    LectureDetailSettingScreen()
}