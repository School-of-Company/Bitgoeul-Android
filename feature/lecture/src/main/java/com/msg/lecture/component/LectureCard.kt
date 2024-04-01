package com.msg.lecture.component

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.R
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import com.msg.model.remote.response.lecture.ContentArray
import java.util.UUID

@Composable
fun LectureCard(
    data: ContentArray,
    onClick: (UUID) -> Unit,
    role: Authority = Authority.ROLE_USER,
    type: LectureType = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM,
) {
    BitgoeulAndroidTheme { color, type ->
        Log.e("data", data.toString())
        Surface {
            Card(
                modifier = Modifier
                    .wrapContentSize(),
                colors = CardDefaults.cardColors(containerColor = color.WHITE)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.lecturer + " 교수",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodySmall,
                    )

                    Text(
                        text = when (data.lectureType) {
                            LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM -> "상호학점인정교육과정"
                            LectureType.UNIVERSITY_EXPLORATION_PROGRAM -> "대학탐방프로그램"
                        },
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G2,
                        style = type.labelMedium,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = data.name,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ContentDescriptionText(
                        maxLines = 2,
                        text = data.content
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${
                                data.startDate.replace("-", ".").substring(0, 10)
                            } ~ ${data.endDate.toString().replace("-", ".").substring(0, 10)}",
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G2,
                            style = type.labelMedium,
                        )

                        Image(
                            painterResource(id = R.drawable.ic_colon_separator),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )

                        Text(
                            text = when (data.semester) {
                                Semester.FIRST_YEAR_FALL_SEMESTER -> "1학년 2학기"
                                Semester.SECOND_YEAR_SPRING_SEMESTER -> "2학년 1학기"
                                Semester.SECOND_YEAR_FALL_SEMESTER -> "2학년 2학기"
                                Semester.THIRD_YEAR_SPRING_SEMESTER -> "3학년 1학기"
                            },
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G2,
                            style = type.labelMedium,
                        )

                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${data.headCount}/${data.maxRegisteredUser}명",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G1,
                        style = type.labelMedium,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        LectureCategoryTag(
                            line = data.line,
                            division = when (data.division) {
                                Division.AUTOMOBILE_INDUSTRY -> "자동차 산업"
                                Division.ENERGY_INDUSTRY -> "에너지 산업"
                                Division.MEDICAL_HEALTHCARE -> "의료헬스케어"
                                Division.AI_CONVERGENCE_AI -> "AI 융복합"
                                Division.CULTURAL_INDUSTRY -> "문화 산업"
                            },
                            department = data.department
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun LectureCardPre() {
    LectureCard(
        data = ContentArray(
            id = UUID.randomUUID(),
            name = "유저 리서치 - 사용자 경험 개선하기",
            content = "청춘! 이는 듣기만 하여도 가슴이 설레는 말이다. 청춘! 너의 두 손을 가슴에 대고, 물방아 같은 심박주홍박주홍박주홍박주홍박주홍박주홍박주홍박주홍박주홍",
            semester = Semester.SECOND_YEAR_SPRING_SEMESTER,
            division = Division.AI_CONVERGENCE_AI,
            department = "컴퓨터공학과",
            startDate = "2021-03-02",
            endDate = "2021-06-02",
            lectureType = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM,
            lectureStatus = LectureStatus.OPEN,
            headCount = 10,
            maxRegisteredUser = 20,
            lecturer = "모시깽이 교수",
            line = "전기전자"
        ),
        onClick = {},
        role = Authority.ROLE_USER,
        type = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM
    )
}