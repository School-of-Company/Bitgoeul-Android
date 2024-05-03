package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.EmptyBoxIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
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
    BitgoeulAndroidTheme { colors, typography ->
        if (data.departments.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.background(color = colors.WHITE)
        ) {
                items(data.departments.size) { index ->
                    LectureDetailSettingDepartmentCard(
                        modifier = modifier,
                        onClick = { department ->
                            onClick(department)
                        },
                        data = data.departments[index]
                    )
                }
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyBoxIcon(
                    modifier = modifier
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "검색 결과가 없습니다.",
                    style = typography.labelMedium,
                    color = colors.G2
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
    division: String,
    onClick: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (searchLineData.lines.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = Color.Transparent)
            ) {
                items(searchLineData.lines.size) { index ->
                    searchLineData.lines.getOrNull(index)?.let { linesData ->
                        LectureDetailSettingInfoCard(
                            modifier = modifier.background(color = Color.Transparent),
                            onClick = { _, searchLineData, _ ->
                                onClick(searchLineData)
                            },
                            searchProfessorData = null,
                            searchLineData = linesData,
                            division = division,
                            keyword = keyword
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyBoxIcon(
                    modifier = modifier
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "검색 결과가 없습니다.",
                    style = typography.labelMedium,
                    color = colors.G2
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
    division: String,
    onClick: (UUID, String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (searchProfessorData.instructors.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = Color.Transparent),
            ) {
                items(searchProfessorData.instructors.size) { index ->
                    searchProfessorData.instructors.getOrNull(index)?.let {
                        LectureDetailSettingInfoCard(
                            modifier = modifier.background(color = Color.Transparent),
                            onClick = { professorUUID, _, selectedProfessorName ->
                                onClick(professorUUID, selectedProfessorName)
                            },
                            searchProfessorData = searchProfessorData.instructors[index],
                            searchLineData = null,
                            division = division,
                            keyword = keyword
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyBoxIcon(
                    modifier = modifier
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "검색 결과가 없습니다.",
                    style = typography.labelMedium,
                    color = colors.G2
                )
            }
        }
    }
}