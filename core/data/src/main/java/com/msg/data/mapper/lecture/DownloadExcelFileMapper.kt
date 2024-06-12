package com.msg.data.mapper.lecture

import com.msg.model.entity.lecture.DownloadExcelFileEntity
import com.msg.network.response.lecture.DownloadExcelFileResponse

fun DownloadExcelFileResponse.toEntity() = DownloadExcelFileEntity(
    fileName = fileName,
    file = file,
)
