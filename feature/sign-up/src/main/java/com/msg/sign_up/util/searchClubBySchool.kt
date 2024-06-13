package com.msg.sign_up.util

import com.msg.model.enumdata.HighSchool

fun String.searchClubBySchool(): List<String> {
    HighSchool.values().forEach {
        if (this == it.school) return it.club else listOf("error")
    }
    return listOf("error")
}

fun String.searchEngSchoolNameByKrSchoolName(): HighSchool {
    HighSchool.entries.forEach {
        if (this == it.school) return it
    }
    return HighSchool.DONGIL_HIGH_SCHOOL_OF_FUTURE_SCIENCE_HIGH_SCHOOL
}