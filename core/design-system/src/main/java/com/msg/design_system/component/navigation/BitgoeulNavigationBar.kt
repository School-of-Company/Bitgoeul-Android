package com.msg.design_system.component.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.theme.color.BitgoeulColor

@Composable
fun RowScope.BitgoeulNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = BitgoeulColor.P5,
            unselectedIconColor = BitgoeulColor.G1,
            selectedTextColor = BitgoeulColor.P5,
            unselectedTextColor = BitgoeulColor.G1,
            indicatorColor = BitgoeulColor.WHITE
        )
    )
}

@Composable
fun BitgoeulNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    BitgoeulAndroidTheme { colors, _ ->
        Column {
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.G9
            )
            NavigationBar(
                modifier = modifier,
                containerColor = colors.WHITE,
                contentColor = colors.G1,
                tonalElevation = 0.dp,
                content = content
            )
        }
    }
}

@Preview
@Composable
fun BitgoeulNavigationPre() {
    val items = listOf(
        "강의",
        "동아리",
        "홈",
        "게시판",
        "내 정보"
    )
    val icons = listOf(
        R.drawable.ic_lecture,
        R.drawable.ic_club,
        R.drawable.ic_home,
        R.drawable.ic_board,
        R.drawable.ic_my
    )
    BitgoeulAndroidTheme { colors, typography ->
        BitgoeulNavigationBar {
            items.forEachIndexed { index, item ->
                BitgoeulNavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item,
                            tint = colors.P5
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            style = typography.labelMedium
                        )
                    },
                    selected = index == 0,
                    onClick = {},
                )
            }
        }
    }
}