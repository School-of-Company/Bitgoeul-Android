package com.msg.lecture.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.R
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.response.lecture.LectureListResponse
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun LectureCard(
    data: LectureListResponse,
    onClick: (UUID) -> Unit,
    role: Authority = Authority.ROLE_USER,
    status: ApproveStatus = ApproveStatus.APPROVED,
    type: LectureType = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM,
) {
    BitgoeulAndroidTheme { color, type ->
        Surface {
            Card(
                modifier = Modifier
                    .wrapContentSize(),
                colors = CardDefaults.cardColors(containerColor = color.WHITE)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = data.lecturer + " 교수",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodySmall,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = data.completeDate.toString().replace("-", ".").substring(0, 10),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G1,
                        style = type.bodySmall,
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
                            text = "${data.startDate.toString().replace("-", ".").substring(0, 10)} ~ ${data.endDate.toString().replace("-", ".").substring(0, 10)}",
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G1,
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
                            text = "${data.headCount}/${data.maxRegisteredUser}명",
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G1,
                            style = type.labelMedium,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        LectureCategoryTag(
                            text = if (data.lectureType == LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM) "상호학점인정교육과정" else "대학탐방프로그램"
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
        data = LectureListResponse(
            id = UUID.randomUUID(),
            name = "유저 리서치 - 사용자 경험 개선하기",
            content = "청춘! 이는 듣기만 하여도 가슴이 설레는 말이다. 청춘! 너의 두 손을 가슴에 대고, 물방아 같은 심장이 박주홍 강민수 두근두근 연애",
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now(),
            completeDate = LocalDateTime.now(),
            lectureType = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM,
            lectureStatus = LectureStatus.OPEN,
            headCount = 50,
            maxRegisteredUser = 100,
            lecturer = "정찬우"
        ),
        onClick = {}
    )
}