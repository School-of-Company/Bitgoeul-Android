package com.msg.model.entity.user

import com.msg.model.enumdata.Authority

data class InquiryMyPageEntity(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val authority: Authority,
    val organization: String
)
