package com.msg.post

import com.msg.model.enumdata.Authority
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.component.icon.ChatIcon
import com.msg.design_system.component.icon.HelpIcon
import com.msg.design_system.component.icon.MegaphoneIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.post.GetPostListEntity
import com.msg.model.enumdata.FeedType
import com.msg.post.viewmodel.PostViewModel
import com.msg.ui.PostList
import java.util.UUID

@Composable
internal fun PostScreenRoute(
    viewModel: PostViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onItemClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val role = viewModel.role
    val state = rememberSaveable { mutableStateOf(viewModel.currentFeedType.value) }

    LaunchedEffect(true, state) {
        viewModel.getPostList(type = state.value)
        getPostList(
            viewModel = viewModel,
            onSuccess = { viewModel.postList.value = it },
            onFailure = { viewModel.postList.value = GetPostListEntity(posts = emptyList()) }
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
            viewModel.postList.value = GetPostListEntity(posts = emptyList())
            viewModel.getPostList(type = it)
            state.value = it
        },
        viewState = state.value,
    )
}

private suspend fun getPostList(
    viewModel: PostViewModel,
    onSuccess: (data: GetPostListEntity) -> Unit,
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
    data: GetPostListEntity,
    viewState: FeedType,
) {
    val roleField = setOf(
        Authority.ROLE_ADMIN,
        Authority.ROLE_BBOZZAK,
        Authority.ROLE_PROFESSOR,
        Authority.ROLE_COMPANY_INSTRUCTOR,
        Authority.ROLE_GOVERNMENT
    ).map { it.toString() }

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
                Spacer(modifier.weight(1f))
                IconButton(
                    onClick = {
                        val newViewState = if (viewState == FeedType.EMPLOYMENT) FeedType.NOTICE else FeedType.EMPLOYMENT
                        onViewChangeClicked(newViewState)
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

@Preview
@Composable
private fun postScreenPreview() {
    PostScreen(
        role = "",
        onAddClicked = {},
        onItemClicked = {},
        onViewChangeClicked = {},
        data = GetPostListEntity(posts = emptyList()),
        viewState = FeedType.EMPLOYMENT
    )
}