package com.msg.network.response.user

import com.msg.model.enumdata.Authority

data class InquiryMyPageResponse(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val authority: Authority,
    val organization: String
)
