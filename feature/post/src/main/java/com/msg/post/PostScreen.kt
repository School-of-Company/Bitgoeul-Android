package com.msg.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.model.post.PostModel
import com.msg.model.remote.response.post.GetPostListResponse
import com.msg.ui.DevicePreviews
import com.msg.ui.PostList
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    role: Authority,
    onAddClicked: () -> Unit,
    onItemClicked: (UUID) -> Unit,
    data: GetPostListResponse
) {
    val roleField = listOf(
        Authority.ROLE_ADMIN,
        Authority.ROLE_BBOZZAK,
        Authority.ROLE_PROFESSOR,
        Authority.ROLE_COMPANY_INSTRUCTOR,
        Authority.ROLE_GOVERNMENT
    )
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(start = 28.dp),
                    text = "게시판 목록",
                    style = typography.titleMedium,
                    color = colors.BLACK
                )
                Spacer(Modifier.weight(1f))
                if (roleField.contains(role)) {
                    IconButton(
                        modifier = modifier.padding(end = 28.dp),
                        onClick = onAddClicked,
                        content = { PlusIcon() }
                    )
                }
            }
            Spacer(modifier = modifier.height(40.dp))
            PostList(
                modifier = modifier,
                data = data,
                onItemClicked = onItemClicked
            ) {
                
            }
        }
    }
}

@DevicePreviews
@Composable
fun PostScreenPre() {
    PostScreen(
        role = Authority.ROLE_ADMIN,
        onAddClicked = {},
        onItemClicked = {},
        data = GetPostListResponse(
            listOf(
                PostModel(
                    id = UUID.randomUUID(),
                    title = "Test Text",
                    modifiedAt = LocalDateTime.now()
                )
            )
        )
    )
}