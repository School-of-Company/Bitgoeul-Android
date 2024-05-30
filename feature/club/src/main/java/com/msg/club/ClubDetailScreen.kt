package com.msg.club

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.club.component.ClubInfoSection
import com.msg.club.component.ClubMemberInfoListSection
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.color.BitgoeulColor
import Authority
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.ui.DevicePreviews
import java.util.UUID

@Composable
fun ClubDetailScreenRoute(
    viewModel: ClubViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onBackClickedByAdmin: () -> Unit
) {
    val isAdmin = viewModel.role == Authority.ROLE_ADMIN.toString()

    if (isAdmin) viewModel.getClubDetail() else viewModel.getMyClubDetail()

    ClubDetailScreen(
        onBackClicked = {
            if (isAdmin) onBackClickedByAdmin()
            else onBackClicked()
        },
        data = viewModel.detailClub.value,
        onItemClicked = {}
    )
}

@Composable
fun ClubDetailScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    data: ClubDetailResponse,
    onItemClicked: (UUID) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = BitgoeulColor.WHITE)
    ) {
        Spacer(modifier = modifier.height(20.dp))
        GoBackTopBar(
            icon = { GoBackIcon() },
            text = "돌아가기",
            onClick = onBackClicked
        )
        Spacer(modifier = modifier.height(16.dp))
        ClubInfoSection(data = data)
        Spacer(modifier = modifier.height(24.dp))
        ClubMemberInfoListSection(
            teacherData = data.teacher,
            studentsData = data.students,
            onItemClicked = onItemClicked
        )
    }
}

@DevicePreviews
@Composable
fun ClubDetailScreenPre() {
    ClubDetailScreen(
        onBackClicked = {},
        data = ClubDetailResponse(
            clubId = 123,
            clubName = "dev_GSM",
            highSchoolName = "광주소프트웨어고등학교",
            headCount = 5,
            students = listOf(
                ClubDetailResponse.Student(
                    id = UUID.randomUUID(),
                    name = "채종인"
                )
            ),
            teacher = ClubDetailResponse.Teacher(
                id = UUID.randomUUID(),
                name = "채종인"
            )
        ),
        onItemClicked = {}
    )
}