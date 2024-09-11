package com.bitgoeul.inquiry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitgoeul.inquiry.component.InquiryFilterButton
import com.bitgoeul.inquiry.component.InquiryList
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.dialog.FilterDialog
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.component.textfield.TrailingIconTextField

@Composable
fun InquiryRoute() {
    val inquiryFilterList = remember { listOf("전체", "답변 된 문의사항만", "대기중인 문의사항만") }

    InquiryScreen(
        onBackClicked = {},
        role = "",
        inquiryFilterList = inquiryFilterList
    )
}

@Composable
fun InquiryScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    inquiryFilterList: List<String>,
    role: String,
) {
    val filterList = remember(inquiryFilterList) { inquiryFilterList }
    val isFilterVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp),
        ) {
            Spacer(modifier = modifier.height(20.dp))

            GoBackTopBar(
                icon = { GoBackIcon() },
                text = stringResource(id = R.string.go_back),
                onClicked = onBackClicked
            )

            Spacer(modifier = modifier.height(8.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.inquiry),
                    style = typography.titleMedium,
                    color = colors.BLACK
                )

                Spacer(modifier = modifier.weight(1f))

                if (role == "ROLE_STUDENT") {
                    PlusIcon(
                        modifier = modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = modifier.height(12.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TrailingIconTextField(
                    modifier = modifier,
                    placeholder = stringResource(id = R.string.inquiry_search_placeholder),
                    onValueChange = {},
                    isDisabled = false,
                    onButtonClicked = {}
                )

                Spacer(modifier = modifier.weight(1f))

                InquiryFilterButton(
                    onClicked = {
                        isFilterVisible.value = true
                    }
                )
            }

            Spacer(modifier = modifier.height(4.dp))

            InquiryList()
        }

        FilterDialog(
            filterItemList = filterList,
            isVisible = isFilterVisible.value,
            onQuit = { isFilterVisible.value = false },
            onItemClicked = {
                // 필터 클릭 처리
            }
        )
    }
}

@Preview
@Composable
fun InquiryScreenPre() {
    InquiryRoute()
}