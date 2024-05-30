package com.msg.model.remote.response.lecture


data class DownloadExcelFileResponse(
    val fileName: String,
    val file: ByteArray,
)
