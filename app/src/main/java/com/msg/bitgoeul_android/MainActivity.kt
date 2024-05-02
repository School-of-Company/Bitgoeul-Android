package com.msg.bitgoeul_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import com.msg.bitgoeul_android.ui.BitgoeulApp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider {
                BitgoeulAndroidTheme { _, _ ->
                    BitgoeulApp(windowSizeClass = calculateWindowSizeClass(this))
                }
            }
        }
    }
}