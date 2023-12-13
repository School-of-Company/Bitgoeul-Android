package com.example.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.ui.CollegeData

@Composable
fun CollegeCardView(
    modifier: Modifier = Modifier,
    data: CollegeData
) {
    BitgoeulAndroidTheme { colors, typography ->
        Card(
            modifier = modifier
                .fillMaxWidth()
        ) {

        }
    }
}