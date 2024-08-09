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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.R
import com.msg.design_system.component.icon.FilterIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureFilterDialog
import com.msg.lecture.component.LectureList
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.entity.lecture.LectureListEntity.Lectures.ContentArray
import com.msg.model.entity.lecture.LectureListEntity
import com.msg.model.entity.lecture.LectureListEntity.Lectures
import com.msg.model.enumdata.LectureStatus
import com.msg.model.enumdata.Semester
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
            viewModel.selectedLectureId.value = id
            viewModel.getDetailLecture(id)
            onItemClicked()
        },
        onFilterChanged = { type ->
            viewModel.lectureList.value = LectureListEntity(
                lectures = Lectures(
                    content = emptyList(),
                    pageable = Lectures.Pageable(
                        sort = Lectures.Sort(
                            empty = true,
                            sorted = false,
                            unsorted = true
                        ),
                        offset = 0L,
                        pageNumber = 0,
                        pageSize = 10,
                        paged = true,
                        unpaged = false
                    ),
                    last = false,
                    totalPages = 0,
                    totalElements = 0,
                    size = 10,
                    number = 0,
                    first = true,
                    sort = Lectures.Sort(
                        empty = true,
                        sorted = false,
                        unsorted = true
                    ),
                    numberOfElements = 0,
                    empty = true
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
    onSuccess: (data: LectureListEntity) -> Unit,
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
    data: LectureListEntity? = null,
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

@Preview
@Composable
private fun LectureListScreenPre() {
    val initialLectureList = LectureListEntity(
        lectures = Lectures(
            content = listOf(
                ContentArray(
                    id = UUID.randomUUID(),
                    name = "코틀린",
                    lecturer = "김코틀린교수",
                    lectureType = "상호학점인정교육과정",
                    startDate = "2021-09-01",
                    endDate = "2021-09-30",
                    lectureStatus = LectureStatus.OPENED,
                    headCount = 10,
                    maxRegisteredUser = 20,
                    department = "컴퓨터공학과",
                    division = "전공",
                    essentialComplete = true,
                    line = "A계열",
                    content = "코틀린은 자바보다 훨씬 좋지롱 코틀린 최고~",
                    semester = Semester.THIRD_YEAR_SPRING_SEMESTER,
                ),
                ContentArray(
                    id = UUID.randomUUID(),
                    name = "자바",
                    lecturer = "김자바교수",
                    lectureType = "상호학점인정교육과정",
                    startDate = "2021-09-01",
                    endDate = "2021-09-30",
                    lectureStatus = LectureStatus.OPENED,
                    headCount = 10,
                    maxRegisteredUser = 20,
                    department = "컴퓨터공학과",
                    division = "전공",
                    essentialComplete = true,
                    line = "A계열",
                    content = "자바가 코틀린보다 더 형이지롱~",
                    semester = Semester.THIRD_YEAR_SPRING_SEMESTER,
                ),
                ContentArray(
                    id = UUID.randomUUID(),
                    name = "안드로이드",
                    lecturer = "김안드로이드교수",
                    lectureType = "상호학점인정교육과정",
                    startDate = "2021-09-01",
                    endDate = "2021-09-30",
                    lectureStatus = LectureStatus.OPENED,
                    headCount = 10,
                    maxRegisteredUser = 20,
                    department = "컴퓨터공학과",
                    division = "전공",
                    essentialComplete = true,
                    line = "A계열",
                    content = "iOS는 안드로이드보다 구리지롱 ~",
                    semester = Semester.THIRD_YEAR_SPRING_SEMESTER,
                )
            ),
            pageable = Lectures.Pageable(
                sort = Lectures.Sort(
                    empty = true,
                    sorted = false,
                    unsorted = true
                ),
                offset = 0L,
                pageNumber = 0,
                pageSize = 10,
                paged = true,
                unpaged = false
            ),
            last = true,
            totalPages = 1,
            totalElements = 3,
            size = 10,
            number = 0,
            first = true,
            sort = Lectures.Sort(
                empty = true,
                sorted = false,
                unsorted = true
            ),
            numberOfElements = 3,
            empty = false
        )
    )


    LectureListScreen(
        data = initialLectureList,
        onOpenClicked = {},
        onItemClicked = {},
        onFilterChanged = { type -> },
        role = "ROLE_ADMIN"
    )
}