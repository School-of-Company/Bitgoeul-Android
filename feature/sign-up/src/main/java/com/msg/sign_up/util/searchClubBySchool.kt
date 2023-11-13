package com.msg.sign_up.util

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.ui.DHSofFSClubList
import com.msg.model.ui.GATClubList
import com.msg.model.ui.GETHSClubList
import com.msg.model.ui.GGCHSClubList
import com.msg.model.ui.GNSHSClubList
import com.msg.model.ui.GSMClubList
import com.msg.model.ui.GTHSClubList
import com.msg.model.ui.JTHSClubList
import com.msg.model.ui.KTHSClubList
import com.msg.model.ui.SGCHSClubList
import com.msg.model.ui.SSTHSClubList
import com.msg.sign_up.data.HighSchoolList

fun String.searchClubBySchool(): List<String> {
    HighSchool.values().forEach {
        if (this == it.school) return it.club else listOf("error")
    }
    return listOf("error")
}