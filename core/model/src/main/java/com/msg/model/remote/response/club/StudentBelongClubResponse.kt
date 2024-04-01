package com.msg.model.remote.response.club

import com.msg.model.remote.enumdatatype.Authority
import java.util.UUID

data class StudentBelongClubResponse(
    val name: String,
    val phoneNumber: Int,
    val email: String,
    val credit: Int
)
