package com.msg.bitgoeul_android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import com.msg.bitgoeul_android.navigation.BitgoeulNavHost
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun BitgoeulApp(
    windowSizeClass: WindowSizeClass,
    appState: BitgoeulAppState = rememberBitgoeulAppState(
        windowSizeClass = windowSizeClass
    )
) {
    BitgoeulAndroidTheme { _, _ ->
        BitgoeulNavHost(appState = appState)
    }
}