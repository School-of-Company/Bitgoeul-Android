package com.msg.sign_up.util

fun searchingInList(flag: String, list: List<String>): List<String> {
    if (flag.isBlank()) return list
    val returnList: MutableList<String> = mutableListOf()
    list.forEach { data ->
        if (data.contains(flag)) returnList.add(data)
    }
    returnList.sort()
    return returnList
}