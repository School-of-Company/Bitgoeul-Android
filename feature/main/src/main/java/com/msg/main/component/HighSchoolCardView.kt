package com.msg.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.enumdata.HighSchool
import com.msg.model.model.school.SchoolModel

@Composable
internal fun HighSchoolCardView(
    modifier: Modifier,
    school: SchoolModel
) {
    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .width(128.dp)
                .height(128.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = school.logoImageUrl,
                    modifier = modifier
                        .width(80.dp)
                        .height(80.dp),
                    contentDescription = "Emblem of ${school.schoolName}"
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = school.schoolName,
                    style = typography.labelMedium,
                    color = colors.G2,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}