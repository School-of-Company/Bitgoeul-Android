package com.msg.network.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeAdapter {
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    @FromJson
    fun fromJson(json: String): LocalTime {
        return LocalTime.parse(json, formatter)
    }

    @ToJson
    fun toJson(value: LocalTime): String {
        return value.format(formatter)
    }
}