package com.msg.model.remote.response.club

import com.msg.model.remote.enumdatatype.Authority
import java.util.UUID

data class StudentBelongClubResponse(
    val id: UUID,
    val name: String,
    val authority: Authority,
)
