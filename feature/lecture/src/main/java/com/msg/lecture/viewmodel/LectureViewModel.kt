package com.msg.lecture.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.msg.model.param.lecture.OpenLectureParam
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
    private val openLectureUseCase: OpenLectureUseCase,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val lectureApplicationCancelUseCase: LectureApplicationCancelUseCase,
    private val searchProfessorUseCase: SearchProfessorUseCase,
    private val searchLineUseCase: SearchLineUseCase,
    private val searchDepartmentUseCase: SearchDepartmentUseCase,
    private val searchDivisionUseCase: SearchDivisionUseCase,
    private val getLectureSignUpHistoryUseCase: GetLectureSignUpHistoryUseCase,
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

    private val _openLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val openLectureResponse = _openLectureResponse.asStateFlow()

    private val _lectureApplicationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationResponse = _lectureApplicationResponse.asStateFlow()

    private val _lectureApplicationCancelResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationCancelResponse = _lectureApplicationCancelResponse.asStateFlow()

    private val _searchProfessorResponse = MutableStateFlow<Event<SearchProfessorEntity>>(Event.Loading)
    val searchProfessorResponse = _searchProfessorResponse.asStateFlow()

    private val _searchLineResponse = MutableStateFlow<Event<SearchLineEntity>>(Event.Loading)
    val searchLineResponse = _searchLineResponse.asStateFlow()

    private val _searchDepartmentResponse = MutableStateFlow<Event<SearchDepartmentEntity>>(Event.Loading)
    val searchDepartmentResponse = _searchDepartmentResponse.asStateFlow()

    private val _searchDivisionResponse = MutableStateFlow<Event<SearchDivisionEntity>>(Event.Loading)
    val searchDivisionResponse = _searchDivisionResponse.asStateFlow()

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

    var searchProfessorData = mutableStateOf(
        SearchProfessorEntity(
            instructors = listOf(
                Instructors(
                    id = UUID.randomUUID(),
                    authority = Authority.ROLE_ADMIN,
                    name = "",
                    organization = ""
                )
            )
        )
    )
        private set

    var searchLineData = mutableStateOf(
        SearchLineEntity(
            lines = listOf(
                ""
            )
        )
    )
        private set

    var searchDepartmentData = mutableStateOf(
        SearchDepartmentEntity(
            departments = listOf(
                ""
            )
        )
    )
        private set

    var searchDivisionData = mutableStateOf(
        SearchDivisionEntity(
            divisions = listOf(
                ""
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

    var content = mutableStateOf("")
        private set

    var credit = mutableIntStateOf(1)
        private set

    var maxRegisteredUser = mutableIntStateOf(0)
        private set

    var semester = mutableStateOf("")
        private set

    var division = mutableStateOf("")
        private set

    var department = mutableStateOf("")
        private set

    var line = mutableStateOf("")
        private set

    var userId = mutableStateOf(UUID.randomUUID())
        private set

    var lectureType = mutableStateOf("")
        private set

    var startDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var endDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var completeDate = mutableStateOf<LocalDate?>(null)
        private set

    var startTime = mutableStateOf<LocalTime?>(null)
        private set

    var endTime = mutableStateOf<LocalTime?>(null)
        private set

    var lectureDates = mutableStateListOf<LectureDates>()
        private set

    var essentialComplete = mutableStateOf(false)
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

    internal fun openLecture(
        name: String,
        content: String,
        semester: String,
        division: String,
        department: String,
        line: String,
        userId: UUID,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        lectureType: String,
        credit: Int,
        maxRegisteredUser: Int,
        essentialComplete: Boolean,
    ) = viewModelScope.launch {
        openLectureUseCase(
            OpenLectureParam(
                name = name,
                content = content,
                semester = semester,
                division = division,
                department = department,
                line = line,
                userId = userId,
                startDate = startDate,
                endDate = endDate,
                lectureDates = lectureDates,
                lectureType = lectureType,
                credit = credit,
                maxRegisteredUser = maxRegisteredUser,
                essentialComplete = essentialComplete,
                address = "",
                locationDetails = ""
            )
        ).onSuccess {
            it.catch { remoteError ->
                _openLectureResponse.value = remoteError.errorHandling()
            }.collect {
                _openLectureResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _openLectureResponse.value = error.errorHandling()
        }
    }

    internal fun addLectureDates() {
        if (completeDate.value != null || startTime.value != null || endTime.value != null) {
            lectureDates.add(
                LectureDates(
                    completeDate = completeDate.value!!,
                    startTime = startTime.value!!,
                    endTime = endTime.value!!
                )
            )
            completeDate.value = null
            startTime.value = null
            endTime.value = null
        }
    }

    internal fun saveLectureDatesList() {
        lectureDates.forEachIndexed { index, _ ->
            if (lectureDates.isEmpty()) lectureDates.removeAt(index)
        }
    }

    internal fun removeLectureDates() {
        if (lectureDates.isNotEmpty()) {
            lectureDates.removeAt(lectureDates.size - 1)
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

    internal fun searchProfessor(
        keyword: String,
    ) = viewModelScope.launch {
        searchProfessorUseCase(
            keyword = keyword
        ).onSuccess {
            it.catch { remoteError ->
                _searchProfessorResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _searchProfessorResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _searchProfessorResponse.value = error.errorHandling()
        }
    }

    internal fun searchLine(
        keyword: String,
        division: String,
    ) = viewModelScope.launch {
        searchLineUseCase(
            keyword = keyword,
            division = division
        ).onSuccess {
            it.catch { remoteError ->
                _searchLineResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _searchLineResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _searchLineResponse.value = error.errorHandling()

        }
    }

    internal fun searchDepartment(
        keyword: String,
    ) = viewModelScope.launch {
        searchDepartmentUseCase(
            keyword = keyword,
        ).onSuccess {
            it.catch { remoteError ->
                _searchDepartmentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _searchDepartmentResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _searchDepartmentResponse.value = error.errorHandling()
        }
    }

    internal fun searchDivision(
        keyword: String,
    ) = viewModelScope.launch {
        searchDivisionUseCase(
            keyword = keyword,
        ).onSuccess {
            it.catch { remoteError ->
                _searchDivisionResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _searchDivisionResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _searchDivisionResponse.value = error.errorHandling()
        }
    }

    internal fun getLectureSignUpHistory(
        studentId: UUID,
    ) = viewModelScope.launch {
        getLectureSignUpHistoryUseCase(
            studentId = studentId
        ).onSuccess {
            it.catch { remoteError ->
                _getLectureSignUpHistoryResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getLectureSignUpHistoryResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getLectureSignUpHistoryResponse.value = error.errorHandling()
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