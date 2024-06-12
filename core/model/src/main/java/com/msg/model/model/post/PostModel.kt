package com.msg.model.model.post

import java.time.LocalDateTime
import java.util.UUID

data class PostModel(
    val id: UUID,
    val title: String,
    val modifiedAt: LocalDateTime
)