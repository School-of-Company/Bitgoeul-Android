package com.bitgoeul.email.util

sealed class Event<out T>(
    val data: T? = null,
) {

    object Loading : Event<Nothing>()

    /**
     * 성공
     */
    class Success<T>(data: T? = null) : Event<T>(data = data)

    /**
     * 400번 요청이 올바르지 않은 경우
     */
    object BadRequest : Event<Nothing>()

    /**
     * 401번 비인증 요청
     */
    object Unauthorized : Event<Nothing>()

    /**
     * 403번 권한이 없음
     */
    object ForBidden : Event<Nothing>()

    /**
     * 404 찾을 수 없는 경우
     */
    object NotFound : Event<Nothing>()

    /**
     * 406 맞는 규격이 없는 경우
     */
    object NotAcceptable : Event<Nothing>()

    /**
     * 408 요청이 너무 오래 걸리는 경우
     */
    object TimeOut : Event<Nothing>()

    /**
     * 409 권한이 없을 때
     */
    object Conflict : Event<Nothing>()

    /**
     * 50X 서버에러
     */
    object Server : Event<Nothing>()

    /**
     * 예상치 못한 에러
     */
    object UnKnown : Event<Nothing>()

}