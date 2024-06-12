package com.msg.model.entity.lecture


data class DownloadExcelFileEntity(
    val fileName: String,
    val file: ByteArray,
)