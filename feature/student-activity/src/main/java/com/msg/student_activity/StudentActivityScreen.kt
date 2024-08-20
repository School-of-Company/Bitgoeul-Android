package com.msg.student_activity

import androidx.activity.ComponentActivity
import com.msg.model.enumdata.Authority
import com.msg.model.enumdata.Authority.Companion.from
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.model.activity.GetStudentActivityModel
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import com.msg.ui.StudentActivityList
import java.util.UUID

@Composable
fun StudentActivityRoute(
    viewModel: StudentActivityViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onAddClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onBackClicked: () -> Unit,
    id: UUID? = null
) {
    val role = viewModel.role

    viewModel.getStudentActivityList(
        role = from(role),
        page = 1,
        size = 20,
        id = id
    )
    LaunchedEffect(true) {
        getActivityList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.studentActivityList.addAll(it)
            }
        )
    }
    StudentActivityScreen(
        data = viewModel.studentActivityList,
        onAddClicked = onAddClicked,
        onItemClicked = {
            onItemClicked()
            viewModel.selectedActivityId.value = it
        },
        onBackClicked = onBackClicked,
        role = from(role)
    )
}

suspend fun getActivityList(
    viewModel: StudentActivityViewModel,
    onSuccess: (data: List<GetStudentActivityModel>) -> Unit
) {
    viewModel.getStudentActivityListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!.content)
            }

            else -> {}
        }
    }
}

@Composable
fun StudentActivityScreen(
    modifier: Modifier = Modifier,
    data: List<GetStudentActivityModel>? = null,
    onAddClicked: () -> Unit,
    onItemClicked: (UUID) -> Unit,
    onBackClicked: () -> Unit,
    role: Authority,
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Surface(modifier = modifier.fillMaxSize()) {
            Column(modifier = modifier.background(colors.WHITE)) {
                Spacer(modifier = modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Spacer(modifier = modifier.height(8.dp))
                Column (modifier = modifier.padding(horizontal = 28.dp)) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "학생 활동",
                            style = typography.titleMedium,
                            color = colors.BLACK
                        )
                        IconButton(
                            onClick = onAddClicked,
                            content = { PlusIcon() }
                        )
                    }
                    if (data != null) {
                        StudentActivityList(
                            data = data,
                            onClick = onItemClicked,
                            role = role
                        )
                    }
                }
            }
        }
    }
}