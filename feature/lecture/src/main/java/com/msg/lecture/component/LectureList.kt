package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.response.lecture.LectureListResponse
import java.util.UUID

@Composable
fun LectureList(
    data: List<LectureListResponse>?,
    onClick: (UUID) -> Unit,
    role: Authority,
    status: ApproveStatus,
    type: LectureType
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            if (data != null) {
                items(data.size) {
                    Column {
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = colors.G9)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LectureCard(
                        data = data[it],
                        onClick = onClick,
                        role = role,
                        status = status,
                        type = type
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                }
            }
        }
    }
}