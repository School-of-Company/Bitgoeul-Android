package com.msg.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.university.GetUniversityEntity
import com.msg.model.entity.university.GetUniversityEntity.University
import com.msg.model.ui.CollegeData

@Composable
internal fun CollegeCardView(
    modifier: Modifier = Modifier,
    data: University,
) {
    var departmentList = ""
    data.departments.forEach {
        departmentList = "$departmentList$it, "
    }
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            )
        ) {
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = data.universityName,
                style = typography.bodyLarge,
                color = colors.P3
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = departmentList.dropLast(1),
                style = typography.bodySmall,
                color = colors.G4
            )
        }
    }
}

@Composable
fun CollegeCardViewList(
    data: GetUniversityEntity
) {
    Column {
        data.universities.forEach {
            CollegeCardView(data = it)
        }
    }
}