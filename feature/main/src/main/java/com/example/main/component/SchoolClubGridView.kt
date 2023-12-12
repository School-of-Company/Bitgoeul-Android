package com.example.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun AutoSchoolClubGridView(
    modifier: Modifier = Modifier,
    school: String,
    rowItems: List<String>,
) {
    val actualItems = rowItems.getActualList()

    BitgoeulAndroidTheme { colors, typography ->
        Column {
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = school,
                style = typography.bodyLarge,
                color = colors.WHITE
            )
            Spacer(modifier = modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (element in actualItems) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        element.forEach {
                            ClubChipView(clubName = it)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun List<String>.getActualList(): List<List<String>> {
    val configuration = LocalConfiguration.current
    val actualItems: MutableList<MutableList<String>> = mutableListOf(mutableListOf())

    var width = 56.dp
    val screenWidth = configuration.screenWidthDp.dp
    var rows = 0

    for (element in this) {
        if (width.plus(element.getClubChipWidth()) < screenWidth) {
            width += element.getClubChipWidth() + 16.dp
            actualItems[rows].add(element)
        } else {
            actualItems.add(mutableListOf(element))
            width = 56.dp + element.getClubChipWidth() + 16.dp
            rows += 1
        }
    }
    return actualItems
}

@Composable
fun String.getClubChipWidth(): Dp {
    val width = remember { mutableStateOf(0.dp) }
    val shouldShowCompose = remember { mutableStateOf(true) }
    val density = LocalDensity.current
    if (shouldShowCompose.value) {
        ClubChipView(
            clubName = this,
            modifier = Modifier.onGloballyPositioned {
                width.value = with(density) { it.size.width.toDp() }
                return@onGloballyPositioned
            }
        )
    }
    shouldShowCompose.value = false
    return width.value
}

@Preview
@Composable
fun LazyGridViewPre() {
    AutoSchoolClubGridView(school = "gsm", rowItems = listOf("금융실무", "소개팅", "취사모"))
}