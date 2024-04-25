package com.msg.network.util

import com.msg.network.exception.BadRequestException
import com.msg.network.exception.ConflictException
import com.msg.network.exception.ForBiddenException
import com.msg.network.exception.NeedLoginException
import com.msg.network.exception.NetworkException
import com.msg.network.exception.NotFoundException
import com.msg.network.exception.OtherException
import com.msg.network.exception.ServerException
import com.msg.network.exception.TimeOutException
import com.msg.network.exception.UnauthorizedException
import com.msg.network.exception.UnknownException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BitgoeulApiHandler<T> {
    private lateinit var httpRequest: suspend () -> T

    fun httpRequest(httpRequest: suspend () -> T) =
        this.apply { this.httpRequest = httpRequest }

    suspend fun sendRequest(): T {
        return try {
            withContext(Dispatchers.IO) {
                httpRequest.invoke()
            }
        } catch (e: HttpException) {
            val message = e.message
            throw when(e.code()) {
                400 -> BadRequestException(
                    message = message
                )
                401 -> UnauthorizedException(
                    message = message
                )
                403 -> ForBiddenException(
                    message = message
                )
                404 -> NotFoundException(
                    message = message
                )
                409 -> ConflictException(
                    message = message
                )
                500, 501, 502, 503 -> ServerException(
                    message = message
                )
                else -> OtherException(
                    message = message,
                    code = e.code()
                )
            }
        } catch (e: SocketTimeoutException) {
            throw TimeOutException(message = e.message)
        } catch (e: UnknownHostException) {
            throw NetworkException()
        } catch (e: NeedLoginException) {
            throw NeedLoginException()
        } catch (e: Exception) {
            throw UnknownException(message = e.message)
        }
    }
}