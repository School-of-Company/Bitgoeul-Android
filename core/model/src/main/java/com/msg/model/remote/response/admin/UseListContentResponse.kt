package com.msg.model.remote.response.admin

import com.msg.model.remote.enumdatatype.ApproveStatus
import java.util.UUID

data class UseListContentResponse(
    val id: UUID,
    val name: String,
    val authority: UseListAuthority,
    val approveStatus: ApproveStatus
)

enum class UseListAuthority {
    ADMIN,
    STUDENT,
    TEACHER,
    PROFESSOR,
    GOVEMMENT,
    COMPANY_INSTRUCDOR,
    BBOZZAK_TEACHER
}
