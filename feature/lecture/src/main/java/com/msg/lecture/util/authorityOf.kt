package com.msg.lecture.util

import com.msg.model.remote.enumdatatype.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

fun Authority.Companion.authorityOf(authority: Flow<Authority>): Flow<Authority> = flow {
    emit(authority.firstOrNull() ?: Authority.ROLE_USER)
}
