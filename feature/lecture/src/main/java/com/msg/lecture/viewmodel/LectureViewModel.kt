package com.msg.lecture.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.LectureApplicationCancelUseCase
import com.msg.domain.lecture.LectureApplicationUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.domain.lecture.SearchDepartmentUseCase
import com.msg.domain.lecture.SearchLineUseCase
import com.msg.domain.lecture.SearchProfessorUseCase
import com.msg.lecture.util.Event
import com.msg.lecture.util.authorityOf
import com.msg.lecture.util.errorHandling
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import com.msg.model.remote.model.lecture.LectureDates
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.ContentArray
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.Instructors
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.Lectures
import com.msg.model.remote.response.lecture.SearchDepartmentResponse
import com.msg.model.remote.response.lecture.SearchLineResponse
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
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
    private val authTokenDataSource: AuthTokenDataSource,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val lectureApplicationCancelUseCase: LectureApplicationCancelUseCase,
    private val searchProfessorUseCase: SearchProfessorUseCase,
    private val searchLineUseCase: SearchLineUseCase,
    private val searchDepartmentUseCase: SearchDepartmentUseCase,
) : ViewModel() {

    suspend fun getRole(): Authority {
        return Authority.authorityOf(authTokenDataSource.getAuthority())
    }

    private val _getLectureListResponse =
        MutableStateFlow<Event<LectureListResponse>>(Event.Loading)
    val getLectureListResponse = _getLectureListResponse.asStateFlow()

    private val _getDetailLectureResponse =
        MutableStateFlow<Event<DetailLectureResponse>>(Event.Loading)
    val getDetailLectureResponse = _getDetailLectureResponse.asStateFlow()

    private val _openLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val openLectureResponse = _openLectureResponse.asStateFlow()

    private val _lectureApplicationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationResponse = _lectureApplicationResponse.asStateFlow()

    private val _lectureApplicationCancelResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationCancelResponse = _lectureApplicationCancelResponse.asStateFlow()

    private val _searchProfessorResponse =
        MutableStateFlow<Event<SearchProfessorResponse>>(Event.Loading)
    val searchProfessorResponse = _searchProfessorResponse.asStateFlow()

    private val _searchLineResponse = MutableStateFlow<Event<SearchLineResponse>>(Event.Loading)
    val searchLineResponse = _searchLineResponse.asStateFlow()

    private val _searchDepartmentResponse =
        MutableStateFlow<Event<SearchDepartmentResponse>>(Event.Loading)
    val searchDepartmentResponse = _searchDepartmentResponse.asStateFlow()

    var lectureList = mutableStateOf(
        LectureListResponse(
            lectures = Lectures(
                content = listOf(
                    ContentArray(
                        id = UUID.randomUUID(),
                        name = "",
                        content = "",
                        startDate = LocalDateTime.now().toString(),
                        endDate = LocalDateTime.now().toString(),
                        lectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
                        lectureStatus = LectureStatus.OPEN,
                        headCount = 0,
                        maxRegisteredUser = 0,
                        lecturer = "",
                        department = "",
                        semester = Semester.SECOND_YEAR_FALL_SEMESTER,
                        division = Division.AI_CONVERGENCE,
                        line = ""
                    )
                )
            )
        )
    )
        private set

    var searchProfessorData = mutableStateOf(
        SearchProfessorResponse(
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
        SearchLineResponse(
            lines = listOf(
                ""
            )
        )
    )
        private set

    var searchDepartmentData = mutableStateOf(
        SearchDepartmentResponse(
            departments = listOf(
                ""
            )
        )
    )
        private set

    var lectureDetailData = mutableStateOf(
        DetailLectureResponse(
            name = "",
            content = "",
            semester = Semester.SECOND_YEAR_FALL_SEMESTER,
            division = Division.AI_CONVERGENCE,
            department = "",
            line = "",
            createAt = LocalDate.now(),
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now(),
            lectureDates = listOf(
                LectureDates(
                    completeDate = LocalDate.now(),
                    startTime = LocalTime.now(),
                    endTime = LocalTime.now()
                )
            ),
            lectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
            lectureStatus = LectureStatus.OPEN,
            headCount = 0,
            maxRegisteredUser = 0,
            isRegistered = true,
            lecturer = "",
            credit = 0
        )
    )
        private set

    var selectedLectureId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    var name = mutableStateOf("")
        private set

    var content = mutableStateOf("")
        private set

    var lecturer = mutableStateOf("")
        private set

    var credit = mutableIntStateOf(1)
        private set

    var headCount = mutableIntStateOf(0)
        private set

    var maxRegisteredUser = mutableIntStateOf(0)
        private set

    var semester = mutableStateOf(Semester.FIRST_YEAR_FALL_SEMESTER)
        private set

    var division = mutableStateOf(Division.AUTOMOBILE_INDUSTRY)
        private set

    var department = mutableStateOf("")
        private set

    var line = mutableStateOf("")
        private set

    var userId = mutableStateOf(UUID.randomUUID())
        private set

    var lectureType = mutableStateOf(LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM)
        private set

    var startDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var endDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var startDateForConversion = mutableStateOf<LocalDate?>(null)
        private set

    var endDateForConversion = mutableStateOf<LocalDate?>(null)
        private set

    var completeDate = mutableStateOf<LocalDate?>(null)
        private set

    var startTime = mutableStateOf<LocalTime?>(null)
        private set

    var endTime = mutableStateOf<LocalTime?>(null)
        private set

    var lectureDates = mutableStateListOf<LectureDates>()
        private set

    fun getLectureList(
        page: Int,
        size: Int,
        type: LectureType?,
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

    fun getDetailLecture(
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

    fun openLecture(
        name: String,
        content: String,
        semester: Semester,
        division: Division,
        department: String,
        line: String,
        userId: UUID,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        lectureType: LectureType,
        credit: Int,
        maxRegisteredUser: Int,
    ) = viewModelScope.launch {
        openLectureUseCase(
            OpenLectureRequest(
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
                maxRegisteredUser = maxRegisteredUser
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

    fun addLectureDates() {
        if (completeDate.value != null || startTime.value != null || endTime.value != null) {
            lectureDates.add(
                LectureDates(
                    completeDate = completeDate.value!!,
                    startTime = startTime.value!!,
                    endTime = endTime.value!!
                )
            )
        }
        Log.e("lectureDates in ViewModel", lectureDates.last().toString())
        Log.e("lectureDates Size in Viewmodel", lectureDates.size.toString())
    }

    fun saveLectureDatesList() {
        lectureDates.forEachIndexed { index, _ ->
            if (lectureDates.isEmpty()) lectureDates.removeAt(index)
        }
    }

    fun removeLectureDates() {
        if (lectureDates.isNotEmpty()) {
            lectureDates.removeAt(lectureDates.size - 1)
        }
    }

    fun lectureApplication(
        id: UUID
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

    fun lectureApplicationCancel(
        id: UUID
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

    fun searchProfessor(
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

    fun searchLine(
        keyword: String,
        division: Division
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

    fun searchDepartment(
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
}