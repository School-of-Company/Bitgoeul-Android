package com.msg.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.design_system.R
import com.msg.design_system.component.checkbox.BitGoeulCheckBox
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme


@Composable
fun FilterDialog(
    modifier: Modifier = Modifier,
    filterItemList: List<String>,
    isVisible: Boolean,
    onQuit: (Boolean) -> Unit,
    onItemClicked: (String) -> Unit,
) {
    if (isVisible) {
        BitgoeulAndroidTheme { colors, typography ->
            Dialog(
                onDismissRequest = { onQuit(false) }
            ) {
                Column(
                    modifier = modifier
                        .background(
                            color = colors.WHITE,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .wrapContentSize()
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.inquiry),
                            style = typography.titleSmall,
                            color = colors.BLACK
                        )
                        Spacer(modifier = modifier.weight(1f))
                        IconButton(
                            modifier = modifier
                                .height(24.dp)
                                .width(24.dp),
                            onClick = { onQuit(false) },
                            content = { CloseIcon() }
                        )
                    }

                    Spacer(modifier = modifier.height(24.dp))

                    LazyColumn {
                        items(filterItemList.size) { index ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BitGoeulCheckBox(
                                    modifier = modifier.wrapContentSize(),
                                    onCheckedChange = {
                                        onItemClicked(filterItemList[index])
                                    }
                                )

                                Spacer(modifier = modifier.width(8.dp))

                                Text(text = filterItemList[index])
                            }

                            Spacer(modifier = modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterDialogPreview() {
    FilterDialog(
        filterItemList = listOf("학교선택", "학년선택", "학기선택"),
        isVisible = true,
        onQuit = {},
        onItemClicked = {}
    )
}
