package com.msg.post

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.post.component.AddLinkSection
import com.msg.ui.DevicePreviews

@Composable
internal fun PostDetailSettingScreenRoute(
    viewModel: PostViewModel,
    onCloseClick: () -> Unit
) {
    PostDetailSettingScreen(
        links = viewModel.links,
        onCloseClick = {
            onCloseClick()
            viewModel.saveLinkList()
        },
        onClickAddButton = { viewModel.addLinks() },
        onValueChanged = { index, links ->
            viewModel.links[index] = links
        }
    )
}

@Composable
fun PostDetailSettingScreen(
    modifier: Modifier = Modifier,
    links: MutableList<String>,
    onCloseClick: () -> Unit,
    onClickAddButton: () -> Unit,
    onValueChanged: (index: Int, item: String) -> Unit
) {

    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }

    val addedLinks = links
    val count = remember { mutableIntStateOf(links.count()) }

    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
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
                    onClick = onCloseClick,
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
        onCloseClick = {},
        onClickAddButton = {},
        onValueChanged = {_,_ ->}
    )
}