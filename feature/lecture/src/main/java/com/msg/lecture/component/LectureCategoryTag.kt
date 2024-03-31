package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import org.w3c.dom.Text

@Composable
fun LectureCategoryTag(
    modifier: Modifier = Modifier,
    line: String,
    division: String,
    department: String,

    ) {
    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = MaterialTheme.shapes.medium.copy(all = CornerSize(18.dp))
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = line,
                modifier = modifier.height(20.dp),
                color = colors.G2,
                style = type.labelMedium,
            )

            Spacer(modifier = modifier.width(8.dp))

            Spacer(
                modifier = modifier
                    .size(1.dp, 14.dp)
                    .background(color = colors.G1)
            )

            Spacer(modifier = modifier.width(8.dp))

            Text(
                text = division,
                modifier = modifier.height(20.dp),
                color = colors.G2,
                style = type.labelMedium,
            )

            Spacer(modifier = modifier.width(8.dp))

            Spacer(
                modifier = modifier
                    .size(1.dp, 14.dp)
                    .background(color = colors.G1)
            )


            Spacer(modifier = modifier.width(8.dp))

            Text(
                text = department,
                modifier = modifier.height(20.dp),
                color = colors.G2,
                style = type.labelMedium,
            )
        }
    }
}

@Preview
@Composable
fun LectureCategoryTagPre() {
    LectureCategoryTag(
        modifier = Modifier,
        line = "전기전자",
        division = "자동차 산업",
        department = "A.I융합기계과(야)",
    )
}