package com.msg.sign_up.util

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

fun String.searchBySchool(): List<String> {
    when (this) {
        "광주공업고등학교" -> return GTHSClubList
        "금파공업고등학교" -> return KTHSClubList
        "전남공업고등학교" -> return JTHSClubList
        "광주여자상업고등학교" -> return GGCHSClubList
        "전남여자상업고등학교" -> return listOf()
        "광주자연과학고등학교" -> return GNSHSClubList
        "광주전자공업고등학교" -> return GETHSClubList
        "동일미래과학고등학교" -> return DHSofFSClubList
        "서진여자고등학교" -> return listOf()
        "숭의과학기술학교" -> return SSTHSClubList
        "송원여자상업고등학교" -> return SGCHSClubList
        "광주자동화설비마이스터고등학교" -> return GATClubList
        "광주소프트웨어마이스터고등학교" -> return GSMClubList
        else -> return listOf()
    }
}