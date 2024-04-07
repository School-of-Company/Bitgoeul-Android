package com.msg.club.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.club.ClubDetailResponse
import java.util.UUID

@Composable
fun ClubMemberInfoListSection(
    modifier: Modifier = Modifier,
    teacherData: ClubDetailResponse.Teacher,
    studentsData: List<ClubDetailResponse.Student>,
    onItemClicked: (UUID) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier.padding(horizontal = 28.dp)
        ) {
            item {
                Text(
                    text = "동아리 인원",
                    style = typography.bodyLarge,
                    color = colors.BLACK
                )
                Spacer(modifier = modifier.height(12.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = colors.G9
                )
                Spacer(modifier = modifier.height(12.dp))
                ClubMemberInfoItem(
                    data = teacherData.name,
                    isTeacher = true,
                    id = teacherData.id,
                    onItemClicked = {}
                )
                Spacer(modifier = modifier.height(12.dp))
            }
            items(studentsData) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = colors.G9
                )
                Spacer(modifier = modifier.height(12.dp))
                ClubMemberInfoItem(
                    data = it.name,
                    isTeacher = false,
                    id = it.id,
                    onItemClicked = onItemClicked
                )
                Spacer(modifier = modifier.height(12.dp))
            }
        }
    }
}
