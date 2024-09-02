package com.msg.club

import com.msg.model.enumdata.Authority
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.club.component.ClubInfoSection
import com.msg.club.component.ClubMemberInfoListSection
import com.msg.club.viewmodel.ClubViewModel
import com.msg.design_system.R
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.color.BitgoeulColor
import com.msg.model.entity.club.ClubDetailEntity
import com.msg.ui.DevicePreviews
import java.util.UUID

@Composable
internal fun ClubDetailScreenRoute(
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
internal fun ClubDetailScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    data: ClubDetailEntity,
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
            text = stringResource(R.string.go_back),
            onClicked = onBackClicked
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
        data = ClubDetailEntity(
            clubId = 123,
            clubName = "dev_GSM",
            highSchoolName = "광주소프트웨어고등학교",
            headCount = 5,
            students = listOf(
                ClubDetailEntity.Student(
                    id = UUID.randomUUID(),
                    name = "채종인"
                )
            ),
            teacher = ClubDetailEntity.Teacher(
                id = UUID.randomUUID(),
                name = "채종인"
            )
        ),
        onItemClicked = {}
    )
}