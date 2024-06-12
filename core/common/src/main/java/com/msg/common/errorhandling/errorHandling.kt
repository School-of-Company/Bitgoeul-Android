package com.msg.common.errorhandling

import android.util.Log
import com.msg.common.event.Event
import com.msg.common.exception.BadRequestException
import com.msg.common.exception.ConflictException
import com.msg.common.exception.ForBiddenException
import com.msg.common.exception.NeedLoginException
import com.msg.common.exception.NotAcceptableException
import com.msg.common.exception.NotFoundException
import com.msg.common.exception.ServerException
import com.msg.common.exception.TimeOutException
import com.msg.common.exception.UnauthorizedException

suspend fun <T> Throwable.errorHandling(
    badRequestAction: suspend () -> Unit = {},
    unauthorizedAction: suspend () -> Unit = {},
    forBiddenAction: suspend () -> Unit = {},
    notFoundAction: suspend () -> Unit = {},
    notAcceptableAction: suspend () -> Unit = {},
    timeOutAction: suspend () -> Unit = {},
    conflictAction: suspend () -> Unit = {},
    serverAction: suspend () -> Unit = {},
    unknownAction: suspend () -> Unit = {},
): Event<T> =
    when (this) {
        is BadRequestException -> {
            errorLog("BadRequestException", message)
            badRequestAction()
            Event.BadRequest
        }
        is UnauthorizedException, is NeedLoginException -> {
            errorLog("UnauthorizedException", message)
            unauthorizedAction()
            Event.Unauthorized
        }
        is ForBiddenException -> {
            errorLog("ForBiddenException", message)
            forBiddenAction()
            Event.ForBidden
        }
        is NotFoundException -> {
            errorLog("NotFoundException", message)
            notFoundAction()
            Event.NotFound
        }
        is NotAcceptableException -> {
            errorLog("NotAcceptableException", message)
            notAcceptableAction()
            Event.NotAcceptable
        }
        is TimeOutException -> {
            errorLog("TimeOutException", message)
            timeOutAction()
            Event.TimeOut
        }
        is ConflictException -> {
            errorLog("ConflictException", message)
            conflictAction()
            Event.Conflict
        }
        is ServerException -> {
            errorLog("ServerException", message)
            serverAction()
            Event.Server
        }
        else -> {
            errorLog("UnKnownException", message)
            unknownAction()
            Event.UnKnown
        }
    }

private fun errorLog(tag: String, msg: String?) {
    Log.d("ErrorHandling-$tag", msg ?: "알 수 없는 오류")
}