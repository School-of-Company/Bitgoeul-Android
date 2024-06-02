package com.msg.bitgoeul_android.navigation

import com.msg.design_system.R

enum class TopLevelDestination(
    val unselectedIcon: Int,
    val iconText: String
) {
    LECTURE(
        unselectedIcon = R.drawable.ic_lecture,
        iconText = "강의"
    ),
    CLUB(
        unselectedIcon = R.drawable.ic_club,
        iconText = "동아리"
    ),
    MAIN(
        unselectedIcon = R.drawable.ic_home,
        iconText = "홈"
    ),
    POST(
        unselectedIcon = R.drawable.ic_board,
        iconText = "게시판"
    ),
    MY_PAGE(
        unselectedIcon = R.drawable.ic_my,
        iconText = "내 정보"
    )
}