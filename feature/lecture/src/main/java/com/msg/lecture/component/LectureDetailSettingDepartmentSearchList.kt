package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.model.lecture.SearchResponseModel
import java.util.UUID

@Composable
fun LectureDetailSettingDepartmentSearchList(
    data: List<SearchResponseModel>,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier.background(color = colors.WHITE)
        ) {
            items(data.size) { index ->
                val searchResponse = data[index]
                val textList = searchResponse.lines

                Column {
                    textList.forEach { text ->
                        LectureDetailSettingDepartmentCard(
                            modifier = modifier,
                            onClick = onClick,
                            data = text
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
    }
}