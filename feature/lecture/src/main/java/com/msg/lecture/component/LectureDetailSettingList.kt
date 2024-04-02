package com.msg.lecture.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.util.UUID

@Composable
fun LectureDepartmentList(
    data: SearchDepartmentResponse,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            items(data.departments.size) {index ->
                LectureDetailSettingDepartmentCard(
                    modifier = modifier,
                    onClick = onClick,
                    data = data.departments[index]
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

@Composable
fun LectureLineList(
    searchLineData: SearchLineResponse,
    keyword: String,
    modifier: Modifier,
    division: Division,
    onClick: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = Color.Transparent)
        ) {
            items(searchLineData.lines.size) { index ->
                searchLineData.lines.getOrNull(index)?.let { linesData ->
                    LectureDetailSettingInfoCard(
                        modifier = modifier.background(color = Color.Transparent),
                        onClick = onClick,
                        searchProfessorData = null,
                        searchLineData = linesData,
                        division = division,
                        keyword = keyword
                    )
                }

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

@Composable
fun LectureProfessorList(
    searchProfessorData: SearchProfessorResponse,
    keyword: String,
    modifier: Modifier,
    division: Division,
    onClick: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = Color.Transparent)
        ) {
            items(searchProfessorData.instructors.size) { index ->
                searchProfessorData.instructors.getOrNull(index)?.let {
                    LectureDetailSettingInfoCard(
                        modifier = modifier.background(color = Color.Transparent),
                        onClick = onClick,
                        searchProfessorData = searchProfessorData.instructors[index],
                        searchLineData = null,
                        division = division,
                        keyword = keyword
                    )
                }

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


