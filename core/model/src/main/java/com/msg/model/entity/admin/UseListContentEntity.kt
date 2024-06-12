package com.msg.model.entity.admin

import com.msg.model.enumdata.ApproveStatus
import com.msg.model.enumdata.Authority
import java.util.UUID

data class UseListContentEntity(
    val id: UUID,
    val name: String,
    val authority: Authority,
    val approveStatus: ApproveStatus
)