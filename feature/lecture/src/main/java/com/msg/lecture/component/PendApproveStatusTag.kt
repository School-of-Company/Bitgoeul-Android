package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun PendApproveStatusTag() {
    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = Modifier
                .background(
                    color = colors.E5,
                    shape = MaterialTheme.shapes.medium.copy(all = CornerSize(18.dp))
                )
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pend_approve_status),
                modifier = Modifier.height(20.dp),
                color = colors.WHITE,
                style = type.labelMedium,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun PendApproveStatusTagPre() {
    PendApproveStatusTag()
}