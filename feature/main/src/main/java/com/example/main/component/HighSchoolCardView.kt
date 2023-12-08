package com.example.main.component

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
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.design_system.R
import java.util.Locale

@Composable
fun HighSchoolCardView(
    modifier: Modifier,
    school: HighSchool
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
                Image(
                    modifier = modifier
                        .width(80.dp)
                        .height(80.dp),
                    painter = painterResource(id = when (school) {
                        HighSchool.GWANGJU_SOFTWARE_MEISTER_HIGH_SCHOOL -> R.mipmap.ic_gsm
                        HighSchool.GWANGJU_TECHNICAL_HIGH_SCHOOL -> R.mipmap.ic_gths
                        HighSchool.KUMPA_TECHNICAL_HIGH_SCHOOL -> R.mipmap.ic_kths
                        HighSchool.JEONNAM_TECHNICAL_HIGH_SCHOOL -> R.mipmap.ic_jths
                        HighSchool.GWANGJU_GIRLS_COMMERCIAL_HIGH_SCHOOL -> R.mipmap.ic_ggchs
                        HighSchool.JEONNAM_GIRLS_COMMERCIAL_HIGH_SCHOOL -> R.mipmap.ic_jgchs
                        HighSchool.GWANGJU_NATURAL_SCIENCE_HIGH_SCHOOL -> R.mipmap.ic_gnshs
                        HighSchool.GWANGJU_ELECTRONIC_TECHNICAL_HIGH_SCHOOL -> R.mipmap.ic_geths
                        HighSchool.DONGIL_HIGH_SCHOOL_OF_FUTURE_SCIENCE_HIGH_SCHOOL -> R.mipmap.ic_dhsoffshs
                        HighSchool.SEOJIN_GIRLS_HIGH_SCHOOL -> R.mipmap.ic_sghs
                        HighSchool.SUNGUI_SCIENCE_TECHNOLOGY_HIGH_SCHOOL -> R.mipmap.ic_ssths
                        HighSchool.SONGWON_GIRLS_COMMERCIAL_HIGH_SCHOOL -> R.mipmap.ic_sgchs
                        HighSchool.GWANGJU_AUTOMATIC_EQUIPMENT_TECHNICAL_HIGH_SCHOOL -> R.mipmap.ic_gaths
                    }),
                    contentDescription = "Emblem of ${school.name}"
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = school.school,
                    style = typography.labelMedium,
                    color = colors.G2,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}