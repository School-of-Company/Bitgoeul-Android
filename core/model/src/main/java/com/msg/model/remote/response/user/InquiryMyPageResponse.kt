package com.msg.model.remote.response.user

import com.msg.model.remote.enumdatatype.Authority

data class InquiryMyPageResponse(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val authority: Authority,
    val organization: String
)
