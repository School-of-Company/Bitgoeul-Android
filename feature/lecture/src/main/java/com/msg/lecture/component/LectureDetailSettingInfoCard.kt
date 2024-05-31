package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.bottomsheet.SelectedIndicator
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.Instructors
import java.util.UUID

@Composable
internal fun LectureDetailSettingInfoCard(
    modifier: Modifier = Modifier,
    searchProfessorData: Instructors?,
    searchLineData: String?,
    division: String,
    keyword: String,
    onClick: (UUID, String, String) -> Unit
) {
    val isSelected = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box {
            Card(
                modifier = modifier
                    .background(color = colors.WHITE)
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
                colors = CardDefaults.cardColors(containerColor = colors.WHITE)
            ) {
                if (searchProfessorData != null) {
                    Text(
                        text = searchProfessorData.name,
                        color = colors.BLACK,
                        style = typography.headlineMedium
                    )

                    Text(
                        text = searchProfessorData.organization,
                        color = colors.G2,
                        style = typography.labelMedium
                    )
                } else if (searchLineData != null) {
                    Text(
                        text = searchLineData,
                        color = colors.BLACK,
                        style = typography.headlineMedium
                    )

                    Text(
                        text = division,
                        color = colors.G2,
                        style = typography.labelMedium
                    )
                } else {
                    Text(
                        text = keyword,
                        color = colors.BLACK,
                        style = typography.headlineMedium
                    )

                    Text(
                        text = division + "에 추가하기...",
                        color = colors.G2,
                        style = typography.labelMedium
                    )
                }
            }

            SelectedIndicator(
                modifier = modifier.align(Alignment.CenterEnd),
                isSelected = isSelected.value,
                onClick = {
                    isSelected.value = true
                    if (searchProfessorData != null) {
                        onClick(searchProfessorData.id, searchLineData.toString(), searchProfessorData.name)
                    }
                }
            )
        }
    }
}
