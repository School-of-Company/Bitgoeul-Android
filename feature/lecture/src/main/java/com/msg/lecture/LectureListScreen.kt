package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.bottomsheet.LectureFilterBottomSheet
import com.msg.design_system.component.icon.FilterIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.lecture.component.LectureCard


@Composable
fun LectureListScreen() {
    var isFilterBottomSheetVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, type ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(
                        modifier = Modifier
                            .width(97.dp)
                            .height(31.dp),
                        text = stringResource(id = R.string.lecture_list),
                        color = colors.BLACK,
                        style = type.titleMedium,
                    )

                    Spacer(modifier = Modifier.width(135.dp))

                    PlusIcon(
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(24.dp))


                    FilterIcon(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .clickable {
                                isFilterBottomSheetVisible.value = true
                            }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 28.dp, end = 28.dp, top = 40.dp)
                ) {
                    items(30) {
                        Column {
                            Spacer(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(color = colors.G9)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LectureCard()

                        Spacer(modifier = Modifier.height(12.dp))

                    }
                }
                LectureFilterBottomSheet(
                    onQuit = { isFilterBottomSheetVisible.value = false },
                    isVisible = isFilterBottomSheetVisible.value
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureListPagePreview() {
    LectureListScreen()
}