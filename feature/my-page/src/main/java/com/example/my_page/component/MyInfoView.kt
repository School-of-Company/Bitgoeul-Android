package com.example.my_page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import Authority
import com.msg.model.remote.response.user.InquiryMyPageResponse

@Composable
internal fun MyInfoView(
    data: InquiryMyPageResponse,
    modifier: Modifier
) {
    val organization = data.organization.split("/")
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = data.name,
                    style = typography.titleMedium,
                    color = colors.BLACK
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = when(data.authority) {
                        Authority.ROLE_ADMIN -> "교육청"
                        Authority.ROLE_STUDENT -> "학생"
                        Authority.ROLE_TEACHER -> "취업 동아리 선생님"
                        Authority.ROLE_BBOZZAK -> "뽀짝 선생님"
                        Authority.ROLE_PROFESSOR -> "대학 교수"
                        Authority.ROLE_COMPANY_INSTRUCTOR -> "기업 강사님"
                        Authority.ROLE_GOVERNMENT -> "유관 기관"
                        else -> ""
                    },
                    style = typography.bodyLarge,
                    color = colors.G1
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            if (data.authority != Authority.ROLE_ADMIN) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = organization[0],
                        style = typography.bodyLarge,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = "소속",
                        style = typography.bodyLarge,
                        color = colors.G1
                    )
                }
            }
            Spacer(modifier = modifier.height(4.dp))
            if (data.authority == Authority.ROLE_STUDENT) {
                Text(
                    text = organization[2],
                    style = typography.bodySmall,
                    color = colors.G2
                )
            }
            if (data.authority in setOf(Authority.ROLE_STUDENT, Authority.ROLE_TEACHER)){
                Text(
                    text = organization[1],
                    style = typography.bodySmall,
                    color = colors.G2
                )
            }
        }
    }
}