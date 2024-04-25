package com.msg.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.model.post.PostModel
import com.msg.ui.util.toDotFormat
import java.time.LocalDateTime
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCard(
    modifier: Modifier,
    onItemClicked: (UUID) -> Unit,
    onKebabClicked: () -> Unit,
    data: PostModel,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colors.WHITE
            ),
            shape = RectangleShape,
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { onItemClicked(data.id) }
                ),
        ) {
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = data.title,
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(
                modifier = modifier.height(12.dp)
            )
            Text(
                text = "${data.modifiedAt.toDotFormat()} 에 게시",
                style = typography.labelMedium,
                color = colors.G1
            )
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun PostCardPre() {
    PostCard(
        modifier = Modifier,
        onItemClicked = {},
        onKebabClicked = {},
        data = PostModel(
            id = UUID.randomUUID(),
            title = "Test Post",
            modifiedAt = LocalDateTime.now()
        )
    )
}