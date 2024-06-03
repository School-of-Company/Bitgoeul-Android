package com.msg.ui

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}