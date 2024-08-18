package com.msg.post

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.post.component.AddLinkSection
import com.msg.post.viewmodel.PostViewModel
import com.msg.ui.DevicePreviews

@Composable
internal fun PostDetailSettingScreenRoute(
    viewModel: PostViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onCloseClicked: () -> Unit
) {

    PostDetailSettingScreen(
        links = viewModel.links,
        onCloseClicked = {
            onCloseClicked()
            viewModel.saveLinkList()
        },
        onClickAddButton = { viewModel.addLinks() },
        onValueChanged = { index, links ->
            viewModel.links[index] = links
        }
    )
}

@Composable
internal fun PostDetailSettingScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    links: MutableList<String>,
    onCloseClicked: () -> Unit,
    onClickAddButton: () -> Unit,
    onValueChanged: (index: Int, item: String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "게시글 세부 설정",
                    style = typography.titleSmall,
                    color = colors.BLACK
                )
                IconButton(
                    onClick = onCloseClicked,
                    content = { CloseIcon() }
                )
            }
            Spacer(modifier = modifier.height(28.dp))
            AddLinkSection(
                modifier = modifier,
                links = links,
                onClickAddButton = onClickAddButton,
                onValueChanged = onValueChanged
            )
        }
    }
}

@DevicePreviews
@Composable
fun PostDetailSettingScreenPre() {
    PostDetailSettingScreen(
        links = mutableListOf(
            "https://youtu.be/AlGd4CP-GrA?si=rOHB1qzfswrbrxKM",
            "https://youtu.be/sv--0Kiry7s?si=PQQBa-lyS8ZxqJTm"
        ),
        onCloseClicked = {},
        onClickAddButton = {},
        onValueChanged = { _, _ -> },
        focusManager = LocalFocusManager.current
    )
}