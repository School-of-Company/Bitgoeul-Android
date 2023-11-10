package com.msg.model.remote.enumdatatype

import com.msg.model.ui.*

enum class HighSchool(val school: String, val club: List<String>) {
    GWANGJU_TECHNICAL_HIGH_SCHOOL("광주공업고등학교", GTHSClubList),
    KUMPA_TECHNICAL_HIGH_SCHOOL("금파공업고등학교", KTHSClubList),
    JEONNAM_TECHNICAL_HIGH_SCHOOL("전남공업고등학교", JTHSClubList),
    GWANGJU_GIRLS_COMMERCIAL_HIGH_SCHOOL("광주여자상업고등학교", GGCHSClubList),
    JEONNAM_GIRLS_COMMERCIAL_HIGH_SCHOOL("전남여자상업고등학교", listOf("empty")),
    GWANGJU_NATURAL_SCIENCE_HIGH_SCHOOL("광주자연과학고등학교", GNSHSClubList),
    GWANGJU_ELECTRONIC_TECHNICAL_HIGH_SCHOOL("광주전자공업고등학교", GETHSClubList),
    DONGIL_HIGH_SCHOOL_OF_FUTURE_SCIENCE_HIGH_SCHOOL("동일미래과학고등학교", DHSofFSClubList),
    SEOJIN_GIRLS_HIGH_SCHOOL("서진여자고등학교", listOf("empty")),
    SUNGUI_SCIENCE_TECHNOLOGY_HIGH_SCHOOL("숭의과학기술고등학교", SSTHSClubList),
    SONGWON_GIRLS_COMMERCIAL_HIGH_SCHOOL("송원여자상업고등학교", SGCHSClubList),
    GWANGJU_AUTOMATIC_EQUIPMENT_TECHNICAL_HIGH_SCHOOL("광주자동화설비마이스터고등학교", GATClubList),
    GWANGJU_SOFTWARE_MEISTER_HIGH_SCHOOL("광주소프트웨어마이스터고등학교", GSMClubList)
}