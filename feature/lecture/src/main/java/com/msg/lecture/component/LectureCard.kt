package com.msg.lecture.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.checkbox.BitGoeulCheckBox
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.component.icon.DivideRectangleIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.ContentArray
import com.msg.model.remote.response.lecture.Students
import java.util.UUID

@Composable
fun LectureListCard(
    modifier: Modifier,
    data: ContentArray,
    onClick: (UUID) -> Unit,
) {
    BitgoeulAndroidTheme { color, type ->
        Surface {
            Card(
                modifier = modifier
                    .wrapContentSize()
                    .clickable {
                        onClick(data.id)
                    },
                colors = CardDefaults.cardColors(containerColor = color.WHITE)
            ) {
                Spacer(modifier = modifier.height(20.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.lecturer + " 교수",
                        modifier = modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodySmall,
                    )

                    Text(
                        text = data.lectureType,
                        modifier = modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G2,
                        style = type.labelMedium,
                    )
                }

                Spacer(modifier = modifier.height(12.dp))

                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = data.name,
                        modifier = modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = modifier.height(12.dp))

                    ContentDescriptionText(
                        maxLines = 2,
                        text = data.content
                    )

                    Spacer(modifier = modifier.height(12.dp))

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${
                                data.startDate.replace("-", ".").substring(0, 10)
                            } ~ ${data.endDate.replace("-", ".").substring(0, 10)}",
                            modifier = modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G2,
                            style = type.labelMedium,
                        )

                        Image(
                            painterResource(id = R.drawable.ic_colon_separator),
                            contentDescription = null,
                            modifier = modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )

                        Text(
                            text = data.semester,
                            modifier = modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G2,
                            style = type.labelMedium,
                        )

                    }

                    Spacer(modifier = modifier.height(4.dp))

                    Text(
                        text = "${data.headCount}/${data.maxRegisteredUser}명",
                        modifier = modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G1,
                        style = type.labelMedium,
                    )

                    Spacer(modifier = modifier.height(12.dp))

                    Row {
                        LectureCategoryTag(
                            line = data.line,
                            division = data.division,
                            department = data.department
                        )
                    }

                    Spacer(modifier = modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun LectureTakingStudentCard(
    modifier: Modifier,
    data: Students,
    onChangeCompleteState: (isComplete: Boolean, studentId: UUID) -> Unit,
) {
    val isComplete = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Card(modifier = modifier
            .wrapContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BitGoeulCheckBox(
                    modifier = modifier, checked = isComplete.value
                ) { isCompleteValue ->
                    onChangeCompleteState(isCompleteValue, data.id)
                    isComplete.value = isCompleteValue
                }

                Spacer(modifier = modifier.width(24.dp))

                Text(
                    text = "${data.grade}학년 ${data.classNumber}반 ${data.classNumber}번 ${data.cohort}기 ${data.name}",
                    style = typography.labelLarge,
                    color = colors.BLACK
                )
            }

            Spacer(modifier = modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.phoneNumber,
                    style = typography.labelMedium,
                    color = colors.G1
                )

                Spacer(modifier = modifier.width(8.dp))

                DivideRectangleIcon()

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = data.email,
                    style = typography.labelMedium,
                    color = colors.G1
                )
            }

            Spacer(modifier = modifier.height(4.dp))

            Text(
                text = data.school.name,
                style = typography.labelMedium,
                color = colors.G1
            )

            Spacer(modifier = modifier.height(4.dp))

            Text(
                text = data.clubName,
                style = typography.labelMedium,
                color = colors.G1
            )
        }
    }
}