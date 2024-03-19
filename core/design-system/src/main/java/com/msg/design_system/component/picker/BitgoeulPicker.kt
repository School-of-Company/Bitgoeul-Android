package com.msg.design_system.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@Composable
fun Picker(
    modifier: Modifier,
    text: String,
) {
    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable {

                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                color = colors.G2,
                style = type.bodySmall,
            )

            PickerArrowIcon(isSelected = false)
        }
    }
}

@Composable
fun SpinnerPicker(
    modifier: Modifier,
    itemList: List<String> = emptyList(),
    itemRange: Iterable<Int> = emptyList(),
    onSelectedItemChange: (value: String) -> Unit
) {
    val listState = rememberLazyListState()
    val pickedItem = remember { mutableStateOf("") }
    val baseLine = remember { mutableStateOf(0f) }

    LaunchedEffect(pickedItem.value) {
        val debounce = Job()

        delay(300L)
        onSelectedItemChange(pickedItem.value)

        debounce.cancel()
    }

    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier.onGloballyPositioned {
                baseLine.value = it.positionInWindow().y + it.size.height / 2
            },
            state = listState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = modifier.height(96.dp))
            }
            itemsIndexed(itemList.ifEmpty { itemRange.map { it.toString() } }) { _, item ->
                val isSelected = remember {
                    mutableStateOf(false)
                }

                if (isSelected.value) {
                    pickedItem.value = item
                }

                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            isSelected.value =
                                baseLine.value in it.positionInWindow().y..it.positionInWindow().y + it.size.height
                        }
                ) {
                    Text(
                        text = item,
                        style = typography.headlineLarge,
                        color = if (isSelected.value) colors.BLACK else colors.G2,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            item {
                Spacer(modifier = modifier.height(96.dp))
            }

        }
    }
}

@Preview
@Composable
fun PickerPre() {
    SpinnerPicker(
        modifier = Modifier, itemList = listOf(
            "00",
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08"
        )
    ) {}
}