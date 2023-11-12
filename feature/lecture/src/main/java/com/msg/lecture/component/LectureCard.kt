package com.msg.lecture.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureCard() {
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
                        text = "정찬우 교수", // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .width(87.dp)
                            .height(22.dp),
                        color = color.BLACK,
                        style = type.bodySmall,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "2023.11.10", // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .width(80.dp)
                            .height(22.dp),
                        color = color.G1,
                        style = type.bodySmall,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "유저 리서치 - 사용자 경험 개선하기",  // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .width(304.dp)
                            .height(26.dp),
                        color = color.BLACK,
                        style = type.bodyLarge,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "청춘! 이는 듣기만 하여도 가슴이 설레는 말이다. 청춘! 너의 두 손을 가슴에 대고, 물방아 같은 심...", // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .width(304.dp)
                            .height(44.dp),
                        color = color.G2,
                        style = type.bodySmall,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Text(
                            text = "2023.10.30 ~ 2023.10.31", // 추후 넘어오는 리스폰스값으로 변경 예정
                            modifier = Modifier
                                .width(154.dp)
                                .height(20.dp),
                            color = color.G1,
                            style = type.labelMedium,
                            fontSize = 14.sp
                        )

                        Image(
                            painterResource(id = R.drawable.ic_colon_separator),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                        Text(
                            text = "50/100명",
                            modifier = Modifier
                                .width(57.dp)
                                .height(20.dp),
                            color = color.G1,
                            style = type.labelMedium,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Box(
                        modifier = Modifier
                            .background(
                                color = color.G9,
                                shape = MaterialTheme.shapes.medium.copy(all = CornerSize(18.dp))
                            )
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),

                        ) {
                        Text(
                            text = "상호학점인정교육과정", // 추후 넘어오는 리스폰스값으로 변경 예정
                            modifier = Modifier.height(20.dp),
                            color = color.G2,
                            style = type.labelMedium,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LectureCardPre() {
    LectureCard()
}