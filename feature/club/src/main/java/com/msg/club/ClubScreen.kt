package com.msg.club

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.club.component.ClubResultList
import com.msg.club.util.Event
import com.msg.club.util.getSchoolNameFromEnum
import com.msg.design_system.component.dialog.GetClubListDialog
import com.msg.design_system.component.icon.BigAlertIcon
import com.msg.design_system.component.icon.GreySettingIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.ui.DevicePreviews

@Composable
fun ClubScreenRoute(
    viewModel: ClubViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onItemClicked: () -> Unit
) {
    val role = viewModel.role
    var key = ""

    if (role != Authority.ROLE_ADMIN) onItemClicked()

    LaunchedEffect(key1 = key) {
        getClubList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.clubList.removeRange(0, viewModel.clubList.size)
                viewModel.clubList.addAll(it)
            },
            onFailure = {
                viewModel.clubList.removeRange(0, viewModel.clubList.size)
            }
        )
    }

    ClubScreen(
        data = viewModel.clubList,
        onSettingClicked = {
            viewModel.getClubList(HighSchool.valueOf(it))
            key = it
        },
        onItemClicked = {
            viewModel.selectedClubId.longValue = it
            onItemClicked()
        }
    )
}

suspend fun getClubList(
    viewModel: ClubViewModel,
    onSuccess: (data: List<ClubListResponse>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getClubListResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {
                onFailure()
            }
        }
    }
}

@Composable
fun ClubScreen(
    modifier: Modifier = Modifier,
    data: List<ClubListResponse>,
    onSettingClicked: (String) -> Unit,
    onItemClicked: (Long) -> Unit
) {
    val scrollState = rememberScrollState()
    val isDialogOpened = remember { mutableStateOf(false) }
    val schoolList = HighSchool.entries.getSchoolNameFromEnum()

    BitgoeulAndroidTheme { colors, typography ->  
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(20.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "동아리 목록",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(
                        content = { GreySettingIcon() },
                        onClick = {
                            isDialogOpened.value = true
                        }
                    )
                }
                Spacer(modifier = modifier.height(40.dp))
                if (data.isNotEmpty()) {
                    ClubResultList(
                        data = data,
                        onItemClicked = { onItemClicked(it) }
                    )
                }
            }
            if (data.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BigAlertIcon()
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        text = "재학중인 학교에 취업동아리가 없거나\n등록되어 있지 않습니다.",
                        style = typography.bodyLarge,
                        color = colors.G1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
            GetClubListDialog(
                onQuit = {
                    isDialogOpened.value = it
                },
                onItemClicked = {
                    onSettingClicked(it)
                },
                list = schoolList,
                isVisible = isDialogOpened.value
            )
    }
}

@DevicePreviews
@Composable
fun ClubScreenPre() {
    ClubScreen(
        data = listOf(

        ),
        onSettingClicked = {},
        onItemClicked = {}
    )
}