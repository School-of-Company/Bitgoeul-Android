package com.msg.network.util

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class LocalDateTimeAdapter {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

    @FromJson
    fun fromJson(json: String): LocalDateTime {
        return try {
            LocalDateTime.parse(json, formatter)
        } catch (e: DateTimeParseException) {
            LocalDateTime.parse(json, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }

    @ToJson
    fun toJson(value: LocalDateTime): String {
        return value.format(formatter)
    }
}
