package com.msg.design_system.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorBottomSheet(
    list: List<String>,
    selectedItem: String,
    itemChange: (value: String) -> Unit,
    onQuit: () -> Unit,
    firstItem: @Composable (() -> Unit) = {},
    lastItem: @Composable (() -> Unit) = {}
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

@Preview
@Composable
fun BottomSheetPre() {
    val list = mutableListOf("SMART JOB PROJECT", "나의 미래는 내가 주인공이다!", "Civil 마스터")
    val selectedItem = remember {
        mutableStateOf("")
    }
    SelectorBottomSheet(
        list = list,
        selectedItem = selectedItem.value,
        itemChange = {
            selectedItem.value = it
        },
        onQuit = {}
    ) {

    }
}