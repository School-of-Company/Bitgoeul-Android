package com.msg.lecture.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.GetAuthorityUseCase
import com.msg.model.entity.lecture.DownloadExcelFileEntity
import com.msg.domain.usecase.lecture.*
import com.msg.model.entity.lecture.*
import com.msg.model.enumdata.*
import com.msg.model.model.lecture.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LectureViewModel @Inject constructor(
    private val getLectureListUseCase: GetLectureListUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val lectureApplicationCancelUseCase: LectureApplicationCancelUseCase,
    private val getTakingLectureStudentListUseCase: GetTakingLectureStudentListUseCase,
    private val editLectureCourseCompletionStatusUseCase: EditLectureCourseCompletionStatusUseCase,
    private val downloadExcelFileUseCase: DownloadExcelFileUseCase,
    private val getAuthorityUseCase: GetAuthorityUseCase,
) : ViewModel() {

    val role = getRole().toString()

    private val _getLectureListResponse = MutableStateFlow<Event<LectureListEntity>>(Event.Loading)
    val getLectureListResponse = _getLectureListResponse.asStateFlow()

    private val _getDetailLectureResponse = MutableStateFlow<Event<DetailLectureEntity>>(Event.Loading)
    val getDetailLectureResponse = _getDetailLectureResponse.asStateFlow()

    private val _lectureApplicationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationResponse = _lectureApplicationResponse.asStateFlow()

    private val _lectureApplicationCancelResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationCancelResponse = _lectureApplicationCancelResponse.asStateFlow()

    private val _getLectureSignUpHistoryResponse = MutableStateFlow<Event<GetLectureSignUpHistoryEntity>>(Event.Loading)
    val getLectureSignUpHistoryResponse = _getLectureSignUpHistoryResponse.asStateFlow()

    private val _getTakingLectureStudentListResponse = MutableStateFlow<Event<GetTakingLectureStudentListEntity>>(Event.Loading)
    val getTakingLectureStudentListResponse = _getTakingLectureStudentListResponse.asStateFlow()

    private val _editPostResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val editPostResponse = _editPostResponse.asStateFlow()

    private val _downloadExcelFileResponse = MutableStateFlow<Event<DownloadExcelFileEntity>>(Event.Loading)
    val downloadExcelFileResponse = _downloadExcelFileResponse.asStateFlow()

    var lectureList = mutableStateOf(
        LectureListEntity(
            lectures = LectureListEntity.Lectures(
                content = listOf(
                    LectureListEntity.Lectures.ContentArray(
                        id = UUID.randomUUID(),
                        name = "",
                        content = "",
                        startDate = LocalDateTime.now().toString(),
                        endDate = LocalDateTime.now().toString(),
                        lectureType = "",
                        lectureStatus = LectureStatus.OPENED,
                        headCount = 0,
                        maxRegisteredUser = 0,
                        lecturer = "",
                        department = "",
                        semester = Semester.FIRST_YEAR_FALL_SEMESTER,
                        division = "",
                        line = "",
                        essentialComplete = false
                    )
                ),
                pageable = LectureListEntity.Lectures.Pageable(
                    sort = LectureListEntity.Lectures.Sort(
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
                totalElements = 1,
                size = 10,
                number = 0,
                first = true,
                sort = LectureListEntity.Lectures.Sort(
                    empty = true,
                    sorted = false,
                    unsorted = true
                ),
                numberOfElements = 1,
                empty = false
            )
        )
    )
        private set

    var lectureSingUpHistoryList = mutableStateOf(
        GetLectureSignUpHistoryEntity(
            lectures = listOf(
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "",
                    lectureType = "",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "",
                    isComplete = false
                )
            )
        )
    )

    var takingLectureStudentList = mutableStateOf(
        GetTakingLectureStudentListEntity(
            students = listOf(
                Students(
                    id = UUID.randomUUID(),
                    email = "",
                    name = "",
                    grade = 1,
                    classNumber = 1,
                    number = 1,
                    phoneNumber = "010-2714-1053",
                    school = HighSchool.GWANGJU_AUTOMATIC_EQUIPMENT_TECHNICAL_HIGH_SCHOOL,
                    clubName = "",
                    cohort = 1,
                    isCompleted = false
                )
            )
        )
    )

    var lectureDetailData = mutableStateOf(
        DetailLectureEntity(
            name = "",
            content = "",
            semester = "",
            division = "",
            department = "",
            line = "",
            createAt = LocalDateTime.now(),
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now(),
            lectureDates = listOf(
                LectureDates(
                    completeDate = LocalDate.now(),
                    startTime = LocalTime.now(),
                    endTime = LocalTime.now()
                )
            ),
            lectureType = "",
            lectureStatus = LectureStatus.OPENED,
            headCount = 0,
            maxRegisteredUser = 0,
            isRegistered = true,
            lecturer = "",
            credit = 0,
            essentialComplete = false,
            locationX = "",
            locationY = "",
            address = "",
            locationDetails = ""
        )
    )
        private set

    var selectedLectureId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    var name = mutableStateOf("")
        private set

    var line = mutableStateOf("")
        private set

    internal fun getLectureList(
        page: Int,
        size: Int,
        type: String?,
    ) = viewModelScope.launch {
        getLectureListUseCase(
            page = page,
            size = size,
            type = type
        ).onSuccess {
            it.catch { remoteError ->
                _getLectureListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getLectureListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getLectureListResponse.value = error.errorHandling()
        }
    }

    internal fun getDetailLecture(
        id: UUID,
    ) = viewModelScope.launch {
        getDetailLectureUseCase(
            id = id
        ).onSuccess {
            it.catch { remoteError ->
                _getDetailLectureResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getDetailLectureResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getDetailLectureResponse.value = error.errorHandling()
        }
    }

    internal fun lectureApplication(
        id: UUID,
    ) = viewModelScope.launch {
        lectureApplicationUseCase(
            id = id
        ).onSuccess {
            it.catch { remoteError ->
                _lectureApplicationResponse.value = remoteError.errorHandling()
            }.collect {
                _lectureApplicationResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _lectureApplicationResponse.value = error.errorHandling()
        }
    }

    internal fun lectureApplicationCancel(
        id: UUID,
    ) = viewModelScope.launch {
        lectureApplicationCancelUseCase(
            id = id
        ).onSuccess {
            it.catch { remoteError ->
                _lectureApplicationCancelResponse.value = remoteError.errorHandling()
            }.collect {
                _lectureApplicationCancelResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _lectureApplicationCancelResponse.value = error.errorHandling()
        }
    }

    internal fun getTakingLectureStudentList() = viewModelScope.launch {
        getTakingLectureStudentListUseCase(
            id = selectedLectureId.value
        ).onSuccess {
            it.catch { remoteError ->
                _getTakingLectureStudentListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getTakingLectureStudentListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getTakingLectureStudentListResponse.value = error.errorHandling()
        }
    }

    internal fun editLectureCourseCompletionStatus(
        studentId: UUID,
        isComplete: Boolean,
    ) = viewModelScope.launch {
        editLectureCourseCompletionStatusUseCase(
            id = selectedLectureId.value,
            studentId = studentId,
            isComplete = isComplete
        ).onSuccess {
            it.catch { remoteError ->
                _editPostResponse.value = remoteError.errorHandling()
            }.collect {
                _editPostResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _editPostResponse.value = error.errorHandling()
        }
    }

    internal fun downloadLectureExcel() = viewModelScope.launch {
        downloadExcelFileUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _downloadExcelFileResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _downloadExcelFileResponse.value = Event.Success(data = response)
                    saveFileToStorage(response)
                }
            }.onFailure { error ->
                _downloadExcelFileResponse.value = error.errorHandling()
            }
    }

    internal fun setLectureDetailData(data: DetailLectureEntity) {
        lectureDetailData.value = data
    }

    private fun saveFileToStorage(
        response: DownloadExcelFileEntity
    ) {
        try {
            val fileName = response.fileName
            val fileContents = response.file
            val context = Application()

            val file = File(context.getExternalFilesDir(null), fileName)
            file.writeBytes(fileContents)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getRole() = viewModelScope.launch {
        getAuthorityUseCase()
    }
}