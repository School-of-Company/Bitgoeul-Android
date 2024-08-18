package com.msg.post

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.button.DetailSettingButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.enumdata.FeedType
import com.msg.post.viewmodel.PostViewModel
import com.msg.ui.DevicePreviews

@Composable
internal fun PostAddScreenRoute(
    viewModel: PostViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val titleValue by viewModel.title.collectAsStateWithLifecycle()
    val contentValue by viewModel.content.collectAsStateWithLifecycle()
    val isFeedType = viewModel.currentFeedType.value

    val typeText = when (isFeedType) {
        FeedType.EMPLOYMENT -> "게시글"
        FeedType.NOTICE -> "공지사항"
    }

    PostAddScreen(
        onBackClicked = onBackClicked,
        onSettingClicked = { title, content ->
            viewModel.onTitleChange(title)
            viewModel.onContentChange(content)
            onSettingClicked()
        },
        onAddClicked = { feedType, title, content ->
            if (viewModel.isEditPage.value) {
                viewModel.editPost(
                    id = viewModel.selectedId.value,
                    feedType = feedType,
                    title = title,
                    content = content
                )
            } else {
                viewModel.sendPost(
                    feedType = feedType,
                    title = title,
                    content = content
                )
            }
            viewModel.onContentChange("")
            viewModel.onTitleChange("")
            viewModel.isEditPage.value = false
            onAddClicked()
        },
        title = titleValue,
        content = contentValue,
        onTitleChange = viewModel::onTitleChange,
        onContentChange = viewModel::onContentChange,
        maxTitleLength = 100,
        typeText = typeText,
        feedType = isFeedType,
    )
}

@Composable
internal fun PostAddScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    scrollState: ScrollState = rememberScrollState(),
    onBackClicked: () -> Unit,
    onSettingClicked: (title: String, content: String) -> Unit,
    onAddClicked: (feedType: FeedType, title: String, content: String) -> Unit,
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    maxTitleLength: Int,
    feedType: FeedType,
    typeText: String
) {
    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
            ) {
                Spacer(modifier = modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Spacer(modifier = modifier.height(16.dp))
                Column(
                    modifier = modifier
                        .padding(horizontal = 28.dp)
                        .verticalScroll(scrollState)
                        .weight(1f)
                ) {
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = { if (it.length <= maxTitleLength) onTitleChange(it) },
                        textStyle = typography.titleSmall,
                        decorationBox = { innerTextField ->
                            if (title.isEmpty()) Text(
                                text = "$typeText 제목 (100자 이내)",
                                style = typography.titleSmall,
                                color = colors.G1
                            )
                            innerTextField()
                        }
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = content,
                        onValueChange = { if (it.length <= maxTitleLength) onContentChange(it) },
                        textStyle = typography.bodySmall,
                        decorationBox = { innerTextField ->
                            if (content.isEmpty()) Text(
                                text = "본문 입력 (1000자 이내)",
                                style = typography.bodySmall,
                                color = colors.G1
                            )
                            innerTextField()
                        }
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                ) {
                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    DetailSettingButton(
                        modifier = modifier.fillMaxWidth(),
                        type = typeText
                    ) {
                        onSettingClicked(title, content)
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    BitgoeulButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "$typeText 추가",
                        state = if (title.isNotEmpty() && content.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
                    ) {
                        onAddClicked(feedType, title, content)
                    }
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun PostAddScreenPre() {
    PostAddScreen(
        onBackClicked = {},
        onSettingClicked = { _, _ -> },
        onAddClicked = { _, _, _ -> },
        feedType = FeedType.NOTICE,
        focusManager = LocalFocusManager.current,
        title = "제목",
        content = "내용",
        onTitleChange = {},
        onContentChange = {},
        maxTitleLength = 100,
        typeText = "공지사항"
    )
}