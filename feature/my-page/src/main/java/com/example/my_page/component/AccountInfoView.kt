package com.example.my_page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.user.InquiryMyPageResponse

@Composable
internal fun AccountInfoView(
    data: InquiryMyPageResponse,
    modifier: Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
       Column(
           modifier = modifier.fillMaxWidth()
       ) {
           Text(
               text = "계정 정보",
               style = typography.bodyLarge,
               color = colors.BLACK
           )
           Spacer(modifier = modifier.height(4.dp))
           Text(
               text = data.email,
               style = typography.bodySmall,
               color = colors.G1
           )
           Spacer(modifier = modifier.height(4.dp))
           Text(
               text = data.phoneNumber,
               style = typography.bodySmall,
               color = colors.G1
           )
       }
    }
}