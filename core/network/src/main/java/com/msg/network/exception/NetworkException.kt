package com.msg.network.exception

class NetworkException : RuntimeException() {
    override val message: String
        get() = "네트워크가 불안정합니다. 데이터나 와이파이 연결 상태를 확인해 주세요"
}