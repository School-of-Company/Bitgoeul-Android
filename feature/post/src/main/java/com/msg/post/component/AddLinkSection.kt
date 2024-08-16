package com.msg.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PlusWhiteIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
internal fun AddLinkSection(
    modifier: Modifier = Modifier,
    links: List<String>,
    onClickAddButton: () -> Unit,
    onValueChanged: (index: Int, item: String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            item {
                Text(
                    text = "링크 입력",
                    style = typography.bodyLarge,
                    color = colors.BLACK
                )

                Spacer(modifier = modifier.height(8.dp))
            }
            itemsIndexed(links) { index, item ->
                DefaultTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = "링크 입력(선택)",
                    value = item,
                    isError = false,
                    isLinked = false,
                    isDisabled = false,
                    isReverseTrailingIcon = false,
                    errorText = "",
                    onValueChange = { onValueChanged(index, it) },
                    onButtonClicked = {}
                )
                Spacer(modifier = modifier.height(12.dp))
            }
            item {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = colors.P5,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 12.dp)
                        .clickable(
                            onClick = onClickAddButton
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = modifier.width(20.dp))
                    Text(
                        text = "링크 추가",
                        style = typography.bodySmall,
                        color = colors.WHITE
                    )
                    Spacer(modifier = modifier.weight(1f))
                    PlusWhiteIcon()
                    Spacer(modifier = modifier.width(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun AddLinkSectionPre() {
    AddLinkSection(
        modifier = Modifier,
        links = listOf(
            "https://youtu.be/AlGd4CP-GrA?si=rOHB1qzfswrbrxKM",
            "https://youtu.be/sv--0Kiry7s?si=PQQBa-lyS8ZxqJTm"
        ),
        onClickAddButton = {},
        onValueChanged = {_,_ ->}
    )
}