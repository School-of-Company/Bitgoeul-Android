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
import com.msg.model.entity.lecture.*
import java.util.UUID

@Composable
internal fun LectureDepartmentList(
    data: SearchDepartmentEntity,
    modifier: Modifier = Modifier,
    onClicked: (String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (data.departments.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = colors.WHITE)
            ) {
                items(data.departments.size) { index ->
                    LectureDetailSettingDepartmentAndDivisionCard(
                        modifier = modifier,
                        onClicked = { department ->
                            onClicked(department)
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
internal fun LectureLineList(
    data: SearchLineEntity,
    keyword: String,
    modifier: Modifier = Modifier,
    division: String,
    onClicked: (String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (data.lines.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = Color.Transparent)
            ) {
                items(data.lines.size) { index ->
                    data.lines.getOrNull(index)?.let { linesData ->
                        LectureDetailSettingInfoCard(
                            modifier = modifier.background(color = Color.Transparent),
                            onClicked = { _, searchLineData, _ ->
                                onClicked(searchLineData)
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
internal fun LectureProfessorList(
    data: SearchProfessorEntity,
    keyword: String,
    modifier: Modifier = Modifier,
    division: String,
    onClicked: (UUID, String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (data.instructors.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = Color.Transparent),
            ) {
                items(data.instructors.size) { index ->
                    data.instructors.getOrNull(index)?.let { professorData ->
                        LectureDetailSettingInfoCard(
                            modifier = modifier.background(color = Color.Transparent),
                            onClicked = { professorUUID, _, selectedProfessorName ->
                                onClicked(professorUUID, selectedProfessorName)
                            },
                            searchProfessorData = professorData,
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

@Composable
internal fun LectureDivisionList(
    data: SearchDivisionEntity,
    modifier: Modifier = Modifier,
    onClicked: (String) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        if (data.divisions.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.background(color = Color.Transparent)
            ) {
                items(data.divisions.size) { index ->
                    data.divisions.getOrNull(index)?.let { divisionData ->
                        LectureDetailSettingDepartmentAndDivisionCard(
                            modifier = modifier.background(color = Color.Transparent),
                            onClicked = { division ->
                                onClicked(division)
                            },
                            data = divisionData
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