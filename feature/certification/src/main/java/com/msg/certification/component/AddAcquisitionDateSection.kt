package com.msg.certification.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import java.time.LocalDate

@Composable
fun AddAcquisitionDateSection(
    modifier: Modifier = Modifier,
    onDatePickerQuit: (LocalDate?) -> Unit,
    acquisitionDate: String
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "취득일",
                style = typography.titleSmall,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(8.dp))
            PickerTextField(
                modifier = modifier.fillMaxWidth(),
                text = acquisitionDate.ifEmpty { "취득일" },
                list = listOf(),
                selectedItem = "",
                onItemChange = {},
                isDatePicker = true,
                onDatePickerQuit = onDatePickerQuit
            )
        }
    }
}