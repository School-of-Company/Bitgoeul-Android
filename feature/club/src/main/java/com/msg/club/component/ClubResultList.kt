package com.msg.club.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.club.ClubListResponse

@Composable
fun ClubResultList(
    modifier: Modifier = Modifier,
    data: List<ClubListResponse>,
    onItemClicked: (Long) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = data.getOrNull(0)?.schoolName ?: "",
                style = typography.titleSmall,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(12.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.G9
            )
            LazyColumn {
                itemsIndexed(data) { _, item ->
                    Spacer(modifier = modifier.height(12.dp))
                    ClubResultItem(
                        data = item,
                        onItemClicked = onItemClicked
                    )
                    Spacer(modifier = modifier.height(12.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = colors.G9
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ClubResultListPre() {
    ClubResultList(
        data = listOf(
            ClubListResponse(
                id = 123,
                name = "HMI 동아리",
                schoolName = "광주자동화설비공업고등학교"
            )
        ),
        onItemClicked = {}
    )
}