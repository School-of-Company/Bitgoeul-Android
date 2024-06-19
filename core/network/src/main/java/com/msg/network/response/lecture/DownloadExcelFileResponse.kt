package com.msg.network.response.lecture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DownloadExcelFileResponse(
    @Json(name = "fileName") val fileName: String,
    @Json(name = "file") val file: ByteArray,
)