package com.msg.design_system.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

enum class Vibration(val timings: LongArray, val amplitudess: IntArray) {
    SUCCESS(longArrayOf(0, 20, 80, 30), intArrayOf(0, 60, 0, 100)),
    WARNING(longArrayOf(0, 20, 40, 20), intArrayOf(0, 120, 0, 80)),
    FAILURE(longArrayOf(0, 20, 40, 20, 40, 30, 30, 60), intArrayOf(0, 80, 0, 80, 0, 150, 0, 80)),
    INTERACT(longArrayOf(0, 10), intArrayOf(0, 90))
}

fun Context.vibe(vibration: Vibration) {
    val vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager: VibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    val repeat = -1 //No repeat

    vibrator.vibrate(VibrationEffect.createWaveform(vibration.timings, vibration.amplitudess, repeat))
}