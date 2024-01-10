package com.msg.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.color.BitgoeulColor
import com.msg.model.remote.response.post.GetPostListResponse
import java.util.UUID

@Composable
fun PostList(
    modifier: Modifier,
    data: GetPostListResponse,
    onItemClicked: (UUID) -> Unit,
    onKebabClicked: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(data.posts.size) {
            PostCard(
                modifier = modifier,
                onItemClicked = onItemClicked,
                onKebabClicked = onKebabClicked,
                data = data.posts[it]
            )
            if (it != data.posts.lastIndex) {
                Spacer(modifier = modifier.height(12.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = BitgoeulColor.G9
                )
            }
        }
    }
}