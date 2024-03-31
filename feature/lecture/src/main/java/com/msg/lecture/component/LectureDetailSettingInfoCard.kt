package com.msg.lecture.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.model.lecture.SearchResponseModel
import com.msg.model.remote.response.lecture.Instructor
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import java.util.UUID

@Composable
fun LectureDetailSettingInfoCard(
    modifier: Modifier,
    searchProfessorData: Instructor?,
    searchData: SearchResponseModel?,
    division: Division,
    keyword: String,
    onClick: (UUID) -> Unit
) {
    Log.e("searchProfessorData", searchProfessorData.toString())
    Log.e("searchData", searchData.toString())
    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Card(
                modifier = modifier
                    .background(color = colors.WHITE)
                    .fillMaxWidth()
                    .clickable {
                        if (searchProfessorData != null) {
                            onClick(searchProfessorData.id)
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.WHITE)
            ) {
                searchProfessorData?.let {
                    Text(
                        text = it.name,
                        color = colors.BLACK,
                        style = typography.labelLarge
                    )

                    Text(
                        text = it.organization,
                        color = colors.G2,
                        style = typography.bodySmall
                    )
                }

                searchData?.let {
                    Text(
                        text = it.lines.toString(),
                        color = colors.BLACK,
                        style = typography.headlineMedium
                    )

                    Text(
                        text = when (division) {
                            Division.AUTOMOBILE_INDUSTRY -> "자동차 산업"
                            Division.ENERGY_INDUSTRY -> "에너지 산업"
                            Division.MEDICAL_HEALTHCARE -> "의료•헬스"
                            Division.AI_CONVERGENCE_AI -> "A.I 융•복합"
                            Division.CULTURAL_INDUSTRY -> "문화 산업"
                        },
                        color = colors.G2,
                        style = typography.bodySmall
                    )
                } ?: run {
                    Log.e("LectureDetailSettingInfoCard", "searchData is null")
                    Text(
                        text = keyword,
                        color = colors.BLACK,
                        style = typography.headlineMedium
                    )

                    Text(
                        text = when (division) {
                            Division.AUTOMOBILE_INDUSTRY -> "자동차 산업에 추가하기..."
                            Division.ENERGY_INDUSTRY -> "에너지 산업에 추가하기..."
                            Division.MEDICAL_HEALTHCARE -> "의료•헬스에 추가하기..."
                            Division.AI_CONVERGENCE_AI -> "A.I 융•복합에 추가하기..."
                            Division.CULTURAL_INDUSTRY -> "문화 산업에 추가하기..."
                        },
                        color = colors.G2,
                        style = typography.bodySmall
                    )
                }
            }
        }
    }
}
