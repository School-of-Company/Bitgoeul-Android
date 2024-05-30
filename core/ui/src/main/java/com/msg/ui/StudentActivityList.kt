package com.msg.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import Authority
import com.msg.model.remote.model.activity.InquiryStudentActivityModel
import java.util.UUID

@Composable
fun StudentActivityList(
    data: List<InquiryStudentActivityModel>,
    onClick: (UUID) -> Unit,
    role: Authority
) {
    BitgoeulAndroidTheme { _, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(data.size) {
                StudentActivityCard(
                    data = data[it],
                    onClick = onClick,
                    role = role
                )
            }
        }
    }
}