package com.msg.sign_up.util

import com.msg.model.remote.enumdatatype.HighSchool

fun String.searchClubBySchool(): List<String> {
    HighSchool.values().forEach {
        if (this == it.school) return it.club else listOf("error")
    }
    return listOf("error")
}

fun String.searchEngSchoolNameByKrSchoolName(): String {
    HighSchool.entries.forEach {
        return if (this == it.school) it.toString() else "Error"
    }
    return "Error"
}