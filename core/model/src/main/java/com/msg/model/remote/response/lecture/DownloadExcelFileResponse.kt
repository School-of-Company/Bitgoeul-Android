package com.msg.model.remote.response.lecture

import java.io.File

data class DownloadExcelFileResponse(
    val fileName: String,
    val file: File,
)
