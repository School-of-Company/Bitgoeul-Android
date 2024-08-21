package com.msg.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.enumdata.Field

@Composable
internal fun ClubBanner(
    modifier: Modifier = Modifier,
    data: GetSchoolListEntity,
    field: Field
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .height(1200.dp)
                .fillMaxWidth()
                .background(color = colors.WHITE)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(96.dp))
                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = field.text,
                    style = typography.titleMedium,
                    color = colors.BLACK
                )
                Spacer(modifier = modifier.height(24.dp))
                Column(
                    modifier = modifier
                        .background(
                            color = colors.BACKGROUND_GRAY,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    data.schools.forEach {
                        AutoSchoolClubGridView(
                            school = it.schoolName,
                            rowItems = it.getSpecificFieldList(field)
                        )
                    }
                }
            }
        }
    }
}