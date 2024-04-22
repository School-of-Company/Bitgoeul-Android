package com.msg.club.util

import androidx.compose.runtime.mutableStateListOf
import com.msg.model.remote.enumdatatype.HighSchool

fun Collection<HighSchool>.getSchoolNameFromEnum(): List<String> {
    val schoolList = mutableStateListOf<String>()
    this.forEach {
        schoolList.add(it.school)
    }
    return schoolList
}