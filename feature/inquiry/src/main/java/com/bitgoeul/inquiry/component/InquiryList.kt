package com.bitgoeul.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.theme.BitgoeulAndroidTheme
import androidx.compose.ui.unit.dp


@Composable
fun InquiryList(
    modifier: Modifier = Modifier,
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .background(color = colors.WHITE)
                .fillMaxSize()
        ) {
            items(10) {
                InquiryCard(
                    role = "ROLE_STUDENT",
                    answerStatus = true
                )

                Spacer(modifier = modifier.height(12.dp))
            }
        }
    }
}

@Preview
@Composable
fun InquiryListPre() {
    InquiryList()
}