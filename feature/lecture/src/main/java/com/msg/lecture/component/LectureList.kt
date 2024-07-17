package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.lecture.LectureListEntity.Lectures.ContentArray
import com.msg.model.entity.lecture.Students
import java.util.UUID

@Composable
internal fun LectureList(
    modifier: Modifier = Modifier,
    data: List<ContentArray>?,
    onClicked: (UUID) -> Unit,
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 28.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            if (data != null) {
                items(data) { item ->
                    Column {
                        Spacer(
                            modifier = modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = colors.G9)
                        )
                    }

                    Spacer(modifier = modifier.height(12.dp))

                    LectureListCard(
                        data = item,
                        onClicked = onClicked,
                    )

                    Spacer(modifier = modifier.height(12.dp))
                }

            }
        }
    }
}

@Composable
internal fun LectureTakingStudentList(
    modifier: Modifier = Modifier,
    data: List<Students>?,
    onChangeCompleteState: (isComplete: Boolean, studentId: UUID) -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .padding(vertical = 24.dp)
                .fillMaxSize()
        ){
            if (data != null) {
                items(data) {item ->
                    LectureTakingStudentCard(
                        modifier = modifier,
                        data = item,
                        onChangeCompleteState = { isCompleteStatus, selectedStudentId ->
                            onChangeCompleteState(isCompleteStatus, selectedStudentId)
                        }
                    )
                    
                    Spacer(modifier = modifier.height(12.dp))
                }
            }
        }
    }
}