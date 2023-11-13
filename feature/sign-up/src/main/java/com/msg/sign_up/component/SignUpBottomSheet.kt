package com.msg.sign_up.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.design_system.component.bottomsheet.SelectorBottomSheet
import com.msg.design_system.component.textfield.DefaultTextField

@Composable
fun SignUpBottomSheet(
    list: List<String>,
    selectedItem: String,
    isSearching: Boolean,
    onSelectedItemChanged: (item: String) -> Unit,
    onQuit: () -> Unit,
    onValueChanged: (String) -> Unit,
    onClickButton: () -> Unit
) {
    SelectorBottomSheet(
        list = list,
        selectedItem = selectedItem,
        itemChange = onSelectedItemChanged,
        onQuit = onQuit,
        firstItem = {
            if (isSearching) {
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "동아리 이름으로 검색",
                    isError = false,
                    isLinked = false,
                    isDisabled = false,
                    isReverseTrailingIcon = false,
                    onValueChange = {
                        onValueChanged(it)
                    },
                    onClickButton = onClickButton,
                    errorText = "",
                )
            }
        }
    ) {

    }
}