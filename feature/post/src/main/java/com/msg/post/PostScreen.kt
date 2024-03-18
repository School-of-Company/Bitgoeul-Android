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
import com.msg.design_system.component.icon.HelpIcon
import com.msg.design_system.component.icon.MegaphoneIcon
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
    onViewChangeClicked: (type: FeedType) -> Unit,
    data: GetPostListResponse
) {
    val roleField = listOf(
        Authority.ROLE_ADMIN,
        Authority.ROLE_BBOZZAK,
        Authority.ROLE_PROFESSOR,
        Authority.ROLE_COMPANY_INSTRUCTOR,
        Authority.ROLE_GOVERNMENT
    )

    var viewState: FeedType = FeedType.EMPLOYMENT

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
                    text = if (viewState == FeedType.EMPLOYMENT) "게시글 목록" else "공지사항",
                    style = typography.titleMedium,
                    color = colors.BLACK
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = {
                        viewState =
                            if (viewState == FeedType.EMPLOYMENT) FeedType.NOTICE else FeedType.EMPLOYMENT
                        onViewChangeClicked(viewState)
                    },
                    content = {
                        when (viewState) {
                            FeedType.EMPLOYMENT -> MegaphoneIcon()
                            FeedType.NOTICE -> ChatIcon()
                        }
                    }
                )
                IconButton(
                    onClick = {},
                    content = { HelpIcon() }
                )
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
                onItemClicked = onItemClicked,
                onKebabClicked = {},
            )
        }
    }
}

@DevicePreviews
@Composable
fun PostScreenPre() {
    PostScreen(
        role = Authority.ROLE_STUDENT,
        onAddClicked = {},
        onItemClicked = {},
        onViewChangeClicked = {},
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