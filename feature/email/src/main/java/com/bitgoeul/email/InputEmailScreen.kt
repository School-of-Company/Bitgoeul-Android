package com.bitgoeul.email

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R

@Composable
fun InputEmailScreen(
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { color, typography ->
        Surface {
            Column(
                modifier = modifier
            ) {
                GoBackTopBar(icon = { GoBackIcon() }, text = stringResource(id = R.string.go_back)) {

                }
            }
        }
    }
}