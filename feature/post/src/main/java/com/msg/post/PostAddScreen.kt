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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

    PostAddScreen(
        onBackClicked = onBackClicked,
        onSettingClicked = { title, content ->
            viewModel.savedTitle.value = title
            viewModel.savedContent.value = content
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
            viewModel.savedContent.value = ""
            viewModel.savedTitle.value = ""
            viewModel.isEditPage.value = false
            onAddClicked()
        },
        savedTitle = viewModel.savedTitle.value,
        savedContent = viewModel.savedContent.value,
        feedType = viewModel.currentFeedType.value
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
    savedTitle: String,
    savedContent: String,
    feedType: FeedType
) {
    val title = remember { mutableStateOf(savedTitle) }
    val content = remember { mutableStateOf(savedContent) }

    val maxTitleLength = 100

    val typeText = when (feedType) {
        FeedType.EMPLOYMENT -> "게시글"
        FeedType.NOTICE -> "공지사항"
    }
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
                        value = title.value,
                        onValueChange = { if (it.length <= maxTitleLength) title.value = it },
                        textStyle = typography.titleSmall,
                        decorationBox = { innerTextField ->
                            if (title.value.isEmpty()) Text(
                                text = "$typeText 제목 (100자 이내)",
                                style = typography.titleSmall,
                                color = colors.G1
                            )
                            innerTextField()
                        }
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = content.value,
                        onValueChange = { if (it.length <= maxTitleLength) content.value = it },
                        textStyle = typography.bodySmall,
                        decorationBox = { innerTextField ->
                            if (content.value.isEmpty()) Text(
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
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    DetailSettingButton(
                        modifier = modifier.fillMaxWidth(),
                        type = typeText
                    ) {
                        onSettingClicked(title.value, content.value)
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    BitgoeulButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "$typeText 추가",
                        state = if (title.value.isNotEmpty() && content.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable
                    ) {
                        onAddClicked(feedType, title.value, content.value)
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
        savedTitle = "",
        savedContent = "",
        feedType = FeedType.NOTICE,
        focusManager = LocalFocusManager.current
    )
}