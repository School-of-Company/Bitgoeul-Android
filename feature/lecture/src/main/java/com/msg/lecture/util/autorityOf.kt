package com.msg.lecture.util

import com.msg.model.remote.enumdatatype.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

suspend fun Authority.Companion.authorityOf(authority: Flow<Authority>): Authority {
    return authority.firstOrNull() ?: Authority.ROLE_USER
}
