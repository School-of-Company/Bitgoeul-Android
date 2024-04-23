package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.lecture.ContentArray
import java.util.UUID

@Composable
fun LectureList(
    modifier: Modifier,
    data: List<ContentArray>?,
    onClick: (UUID) -> Unit,
) {
    BitgoeulAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 28.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            if (data != null) {
                items(data) { item ->
                    Column {
                        Spacer(
                            modifier = modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = colors.G9)
                        )
                    }

                    Spacer(modifier = modifier.height(12.dp))

                    LectureCard(
                        modifier = modifier,
                        data = item,
                        onClick = onClick,
                    )

                    Spacer(modifier = modifier.height(12.dp))
                }

            }
        }
    }
}