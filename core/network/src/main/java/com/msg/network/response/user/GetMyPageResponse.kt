package com.msg.network.response.user

import com.msg.model.enumdata.Authority

data class GetMyPageResponse(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val authority: Authority,
    val organization: String
)
