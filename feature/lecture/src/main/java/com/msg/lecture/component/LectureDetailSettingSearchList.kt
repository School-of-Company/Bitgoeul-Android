package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.util.UUID
import androidx.compose.foundation.lazy.items
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.model.lecture.SearchResponseModel

@Composable
fun LectureDetailSettingSearchList(
    searchProfessorData: SearchProfessorResponse?,
    searchData: SearchResponseModel?,
    searchAPIType: String,
    keyword: String,
    modifier: Modifier,
    division: Division,
    onClick: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = Color.Transparent)
        ) {
            when (searchAPIType) {
                "담당 교수" -> {
                    if (searchProfessorData != null) {
                        items(listOf(searchProfessorData)) {
                            Column(
                                modifier = modifier.background(color = Color.Transparent)
                            ) {
                                LectureDetailSettingInfoCard(
                                    modifier = modifier.background(color = Color.Transparent),
                                    onClick = onClick,
                                    searchProfessorData = searchProfessorData,
                                    searchData = null,
                                    division = division,
                                    keyword = keyword
                                )


                                Spacer(
                                    modifier = Modifier
                                        .height(1.dp)
                                        .fillMaxWidth()
                                        .background(color = colors.G9)
                                )
                            }
                        }
                    }
                }

                "강의 계열" -> {
                        if (searchData != null) {
                            items(listOf(searchData)) {
                                Column(
                                    modifier = modifier.background(color = Color.Transparent)
                                ) {
                                    LectureDetailSettingInfoCard(
                                        modifier = modifier.background(color = Color.Transparent),
                                        onClick = onClick,
                                        searchProfessorData = null,
                                        searchData = searchData,
                                        division = division,
                                        keyword = keyword
                                    )


                                    Spacer(
                                        modifier = Modifier
                                            .height(1.dp)
                                            .fillMaxWidth()
                                            .background(color = colors.G9)
                                    )
                                }
                            }
                        }
                }
                    else -> {}
            }
        }
    }
}