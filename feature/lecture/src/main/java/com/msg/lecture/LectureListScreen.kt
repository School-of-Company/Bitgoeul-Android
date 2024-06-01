package com.msg.lecture

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.R
import com.msg.design_system.component.icon.FilterIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureFilterDialog
import com.msg.lecture.component.LectureList
import com.msg.lecture.util.Event
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.Lectures
import java.util.UUID

@Composable
internal fun LectureListRoute(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
) {
    val role = viewModel.role
    val type = remember { mutableStateOf(null) }

    LaunchedEffect(true) {
        viewModel.getLectureList(
            page = 0,
            size = 10,
            type = type.value
        )
        getLectureList(
            viewModel = viewModel,
            onSuccess = { response ->
                viewModel.lectureList.value = response
            }
        )
    }
    LectureListScreen(
        data = viewModel.lectureList.value,
        onOpenClicked = onOpenClicked,
        onItemClicked = { id ->
            onItemClicked()
            viewModel.selectedLectureId.value = id
            viewModel.getDetailLecture(id)
        },
        onFilterChanged = { type ->
            viewModel.lectureList.value = LectureListResponse(
                lectures = Lectures(
                    content = emptyList()
                )
            )
            viewModel.getLectureList(
                page = 0,
                size = 10,
                type = type
            )
        },
        role = role,
    )
}

private suspend fun getLectureList(
    viewModel: LectureViewModel,
    onSuccess: (data: LectureListResponse) -> Unit,
) {
    viewModel.getLectureListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}


@Composable
internal fun LectureListScreen(
    modifier: Modifier = Modifier,
    data: LectureListResponse? = null,
    onOpenClicked: () -> Unit,
    onItemClicked: (UUID) -> Unit,
    onFilterChanged: (type: String?) -> Unit,
    role: String,
) {
    val isFilterDialogVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(20.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = modifier.width(28.dp))

                    Text(
                        modifier = modifier
                            .width(97.dp)
                            .height(31.dp),
                        text = stringResource(id = R.string.lecture_list),
                        color = colors.BLACK,
                        style = typography.titleMedium,
                    )

                    Spacer(modifier = modifier.weight(1f))

                    if (role == "ROLE_ADMIN") {
                        IconButton(
                            onClick = onOpenClicked,
                            modifier = modifier.padding(top = 4.dp),
                            content = { PlusIcon() }
                        )

                        Spacer(modifier = modifier.width(24.dp))

                    }

                    FilterIcon(
                        modifier = modifier
                            .padding(end = 8.dp)
                            .clickable {
                                isFilterDialogVisible.value = !isFilterDialogVisible.value
                            }
                    )

                    if (role != "ROLE_ADMIN") {
                        Text(
                            text = "필터",
                            color = colors.G1,
                            style = typography.bodySmall,
                        )
                    }
                    Spacer(modifier = modifier.width(28.dp))

                }

                Spacer(modifier = modifier.height(40.dp))

                if (data != null) {
                    LectureList(
                        data = data.lectures.content,
                        onClicked = onItemClicked,
                    )
                }

                LectureFilterDialog(
                    isVisible = isFilterDialogVisible.value,
                    onCloseButtonClicked = {
                        isFilterDialogVisible.value = false
                    }
                ) { lectureType ->
                    onFilterChanged(lectureType)
                }
            }
        }
    }
}