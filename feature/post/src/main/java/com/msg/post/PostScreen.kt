package com.msg.post

import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.icon.ChatIcon
import com.msg.design_system.component.icon.HelpIcon
import com.msg.design_system.component.icon.MegaphoneIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import Authority
import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.model.post.PostModel
import com.msg.model.remote.response.post.GetPostListResponse
import com.msg.post.util.Event
import com.msg.ui.DevicePreviews
import com.msg.ui.PostList
import java.time.LocalDateTime
import java.util.UUID

@Composable
internal fun PostScreenRoute(
    viewModel: PostViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onItemClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val role = viewModel.role
    var state = FeedType.EMPLOYMENT

    LaunchedEffect(true, state) {
        viewModel.getPostList(
            type = state
        )
        getPostList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.postList.value = it
            },
            onFailure = {
                viewModel.postList.value = GetPostListResponse(
                    posts = emptyList()
                )
            }
        )
    }

    PostScreen(
        role = role,
        onAddClicked = {
            onAddClicked()
            viewModel.currentFeedType.value = it
        },
        onItemClicked = {
            onItemClicked()
            viewModel.selectedId.value = it
            viewModel.getDetailPost(it)
        },
        data = viewModel.postList.value,
        onViewChangeClicked = {
            viewModel.postList.value = GetPostListResponse(
                posts = emptyList()
            )
            viewModel.getPostList(type = it)
            state = it
        },
        feedType = viewModel.currentFeedType.value
    )
}

private suspend fun getPostList(
    viewModel: PostViewModel,
    onSuccess: (data: GetPostListResponse) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getPostListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {
                onFailure()
            }
        }
    }
}

@Composable
internal fun PostScreen(
    modifier: Modifier = Modifier,
    role: String,
    onAddClicked: (feedType: FeedType) -> Unit,
    onItemClicked: (UUID) -> Unit,
    onViewChangeClicked: (type: FeedType) -> Unit,
    data: GetPostListResponse,
    feedType: FeedType = FeedType.EMPLOYMENT
) {
    val roleField = listOf(
        Authority.ROLE_ADMIN.toString(),
        Authority.ROLE_BBOZZAK.toString(),
        Authority.ROLE_PROFESSOR.toString(),
        Authority.ROLE_COMPANY_INSTRUCTOR.toString(),
        Authority.ROLE_GOVERNMENT.toString()
    )

    var viewState: FeedType = feedType

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
                        onClick = { onAddClicked(viewState) },
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