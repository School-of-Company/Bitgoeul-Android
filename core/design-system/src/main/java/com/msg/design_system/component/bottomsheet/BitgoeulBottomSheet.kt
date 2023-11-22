package com.msg.design_system.component.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.checkbox.BitGoeulCheckBox
import com.msg.design_system.theme.BitgoeulAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorBottomSheet(
    list: List<String>,
    selectedItem: String,
    itemChange: (value: String) -> Unit,
    onQuit: () -> Unit,
    firstItem: @Composable (() -> Unit) = {},
    lastItem: @Composable (() -> Unit) = {},
) {
    ModalBottomSheet(
        onDismissRequest = {
            onQuit()
        },

        ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 28.dp)
        ) {
            item {
                firstItem()
            }
            items(list.size) {
                Selector(value = list[it], isSelected = selectedItem == list[it]) {
                    itemChange(list[it])
                }
            }
            item {
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LectureFilterBottomSheet(
    onQuit: () -> Unit,
    isVisible: Boolean,
) {
    // arrayList<Boolean> 고려
    val isChecked = remember { mutableStateListOf(false, false, false, false) }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (isVisible) {
        BitgoeulAndroidTheme { colors, type ->
            ModalBottomSheet(
                onDismissRequest = {
                    onQuit()
                },
                sheetState = bottomSheetState
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.filter),
                        modifier = Modifier
                            .width(35.dp)
                            .height(26.dp),
                        color = colors.BLACK,
                        style = type.bodySmall,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_category),
                        modifier = Modifier
                            .width(67.dp)
                            .height(26.dp),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[0],
                            onCheckedChange = { isChecked[0] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.mutual_credit_recognition_curriculum),
                            modifier = Modifier
                                .width(139.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[1],
                            onCheckedChange = { isChecked[1] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.university_visit_program),
                            modifier = Modifier
                                .width(111.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_status),
                        modifier = Modifier
                            .width(67.dp)
                            .height(26.dp),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        fontSize = 18.sp
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[2],
                            onCheckedChange = { isChecked[2] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.pend_approve_status),
                            modifier = Modifier
                                .width(78.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[3],
                            onCheckedChange = { isChecked[3] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.approve_status),
                            modifier = Modifier
                                .width(42.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    BitgoeulButton(
                        text = stringResource(id = R.string.apply),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        state = ButtonState.Enable
                    ) {
                        // 클릭시 필터 적용 시키기
                    }

                    Box(
                        modifier = Modifier
                            .height(38.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TimerBottomSheet(
    onQuit: () -> Unit,
    isVisible: Boolean,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val timePickerState = rememberTimePickerState()
   // val chooseHour = remember { mutableStateOf(ch)}
    if (isVisible) {
        BitgoeulAndroidTheme { colors, type ->
            ModalBottomSheet(
                onDismissRequest = {
                    onQuit()
                },
                sheetState = bottomSheetState
            ) {
            }
        }
    }
}

@Composable
fun InfiniteItemsPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {
    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("")}

    BitgoeulAndroidTheme { colors, type ->
        LaunchedEffect(key1 = !listState.isScrollInProgress) {
            onItemSelected(currentValue.value)
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        }

        Box(modifier = modifier.fillMaxHeight()) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,state = listState, content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if(it == listState.firstVisibleItemIndex +1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = items[index], modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),)
                })
            })
        }
    }
}

@Composable
fun CalenderBottomSheet() {

}

@Preview
@Composable
fun BottomSheetPre() {
//    val list = mutableListOf("SMART JOB PROJECT", "나의 미래는 내가 주인공이다!", "Civil 마스터")
//    val selectedItem = remember {
//        mutableStateOf("")
//    }
//    SelectorBottomSheet(
//        list = list,
//        selectedItem = selectedItem.value,
//        itemChange = {
//            selectedItem.value = it
//        },
//        onQuit = {}
//    ) {
//
//    }
//
//    LectureFilterBottomSheet(
//        onQuit = {},
//        isVisible = true
//    )

    TimerBottomSheet(
        onQuit = {},
        isVisible = true,
    )
}