package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import org.w3c.dom.Text

@Composable
fun LectureCategoryTag(
    modifier: Modifier = Modifier,
    text: String,
) {
    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = MaterialTheme.shapes.medium.copy(all = CornerSize(18.dp))
                )
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),

            ) {
            Text(
                text = text,
                modifier = modifier.height(20.dp),
                color = colors.G2,
                style = type.labelMedium,
            )

        }
    }
}