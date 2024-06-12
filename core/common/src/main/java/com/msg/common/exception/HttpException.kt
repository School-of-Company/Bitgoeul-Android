package com.msg.common.exception

class BadRequestException(
    override val message: String?
) : RuntimeException()

class UnauthorizedException(
    override val message: String?
) : RuntimeException()

class ForBiddenException(
    override val message: String?
) : RuntimeException()

class NotFoundException(
    override val message: String?
) : RuntimeException()

class NotAcceptableException(
    override val message: String?
) : RuntimeException()

class ConflictException(
    override val message: String?
) : RuntimeException()

class TimeOutException(
    override val message: String?
) : RuntimeException()

class ServerException(
    override val message: String?
) : RuntimeException()

class OtherException(
    override val message: String?,
    val code: Int
) : RuntimeException()

class UnknownException(
    override val message: String?
) : RuntimeException()