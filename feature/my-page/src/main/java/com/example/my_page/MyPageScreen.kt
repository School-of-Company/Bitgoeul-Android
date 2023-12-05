package com.example.my_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my_page.component.AccountInfoView
import com.example.my_page.component.AccountSettingView
import com.example.my_page.component.MyInfoView
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.response.user.InquiryMyPageResponse

@Composable
fun MyPageScreen(
    onPasswordChangeClicked: () -> Unit,
    onLogOutClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    data: InquiryMyPageResponse,
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "내 정보",
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(32.dp))
            MyInfoView(
                data = data,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(40.dp))
            AccountInfoView(
                data = data,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(68.dp))
            AccountSettingView(
                modifier = modifier,
                onPasswordChangeClicked = onPasswordChangeClicked,
                onLogOutClicked = onLogOutClicked,
                onWithdrawClicked = onWithdrawClicked
            )
            Spacer(modifier = modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun MyPageScreenPre() {
    MyPageScreen(
        onPasswordChangeClicked = {},
        onLogOutClicked = {},
        onWithdrawClicked = {},
        data = InquiryMyPageResponse(
            name = "채종인",
            email = "bitgoeul@gmail.com",
            phoneNumber = "010-0000-0000",
            authority = Authority.ROLE_STUDENT,
            organization = "광주소프트웨어마이스터고등학교/dev.GSM/2학년 3반 16번"
        )
    )
}