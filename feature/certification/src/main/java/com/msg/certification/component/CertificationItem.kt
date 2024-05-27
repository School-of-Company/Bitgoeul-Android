package com.msg.certification.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.certification.CertificationListResponse
import com.msg.design_system.R
import com.msg.ui.util.toDotFormat
import java.time.LocalDate
import java.util.UUID

@Composable
fun CertificationItem(
    modifier: Modifier = Modifier,
    data: CertificationListResponse,
    onEditClicked: (id: UUID, title: String, date: LocalDate) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.name,
                    style = typography.bodyMedium,
                    color = colors.BLACK
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    modifier = modifier.clickable {
                        onEditClicked(data.certificationId, data.name, data.acquisitionDate)
                    },
                    text = "수정",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        fontWeight = FontWeight(400),
                        color = colors.G1,
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = data.acquisitionDate.toDotFormat(),
                style = typography.labelMedium,
                color = colors.G1
            )
        }
    }
}