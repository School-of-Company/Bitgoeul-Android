package com.msg.network.response.lecture

data class DownloadExcelFileResponse(
    val fileName: String,
    val file: ByteArray,
)