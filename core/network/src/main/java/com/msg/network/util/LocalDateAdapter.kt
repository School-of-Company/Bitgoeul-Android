package com.msg.network.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @FromJson
    fun fromJson(json: String): LocalDate {
        return LocalDate.parse(json, formatter)
    }

    @ToJson
    fun toJson(value: LocalDate): String {
        return value.format(formatter)
    }
}