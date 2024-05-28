package com.msg.network.util

import com.google.common.hash.Hashing
import java.security.AlgorithmParameters
import java.util.Arrays
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESCryptUtil {
    fun encryptAES(data: String, secretKey: String): String {
        return try {
            val secretKeys = Arrays.copyOfRange(Hashing.sha1().hashString(secretKey, Charsets.UTF_8).asBytes(), 0, 16)
            val secret: SecretKey = SecretKeySpec(secretKeys, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secret)

            val params: AlgorithmParameters = cipher.parameters
            val iv = params.getParameterSpec(IvParameterSpec::class.java).iv
            val cipherText = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

            iv.toHex() + cipherText.toHex()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}

