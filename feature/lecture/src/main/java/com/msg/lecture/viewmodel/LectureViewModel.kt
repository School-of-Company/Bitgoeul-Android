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
import com.msg.model.remote.model.lecture.SearchResponseModel
import com.msg.model.remote.response.lecture.*
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.ContentArray
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.Lectures
import com.msg.model.remote.response.lecture.SearchProfessorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LectureViewModel @Inject constructor(
    private val getLectureListUseCase: GetLectureListUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val openLectureUseCase: OpenLectureUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
    private val lectureApplicationCancelUseCase: LectureApplicationCancelUseCase,
    private val searchProfessorUseCase: SearchProfessorUseCase,
    private val searchLineUseCase: SearchLineUseCase,
    private val searchDepartmentUseCase: SearchDepartmentUseCase,
) : ViewModel() {
    private val current = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

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

    private val _searchProfessorResponse = MutableStateFlow<Event<List<SearchProfessorResponse>>>(Event.Loading)
    val searchProfessorResponse = _searchProfessorResponse.asStateFlow()

    private val _searchLineResponse = MutableStateFlow<Event<SearchResponseModel>>(Event.Loading)
    val searchLineResponse = _searchLineResponse.asStateFlow()

    private val _searchDepartmentResponse = MutableStateFlow<Event<SearchResponseModel>>(Event.Loading)
    val searchDepartmentResponse = _searchDepartmentResponse.asStateFlow()

    var lectureList = mutableStateOf(
        LectureListResponse(
            lectures = Lectures(
                content = listOf(
                    ContentArray(
                        id = UUID.randomUUID(),
                        name = "",
                        content = "",
                        startDate = current.toString(),
                        endDate = current.toString(),
                        completeDate = current.toString(),
                        lectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
                        lectureStatus = LectureStatus.OPEN,
                        headCount = 0,
                        maxRegisteredUser = 0,
                        lecturer = "",
                        department = "",
                        semester = Semester.SECOND_YEAR_FALL_SEMESTER,
                        division = Division.AI_CONVERGENCE_AI
                    )
                )
            )
        )
    )
        private set

    var lectureDetailData = mutableStateOf(
        DetailLectureResponse(
            name = "",
            content = "",
            semester = Semester.SECOND_YEAR_FALL_SEMESTER,
            division = Division.AI_CONVERGENCE_AI,
            department = "",
            line = "",
            createAt = LocalDate.parse(current.toString().substringBefore("T")),
            startDate = current,
            endDate = current,
            lectureDates = listOf(
                LectureDates(
                    completeDate = LocalDate.parse(current.toString().substringBefore("T")),
                    startTime = LocalTime.parse(current.toString().substringAfter("T").substringBefore("Z")),
                    endTime = LocalTime.parse(current.toString().substringAfter("T").substringBefore("Z"))
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
//    val name: String,
//    val content: String,
//    val semester: Semester,
//    val division: Division,
//    val department: String,
//    val line: String,
//    val createAt: LocalDate,
//    val startDate: LocalDateTime,
//    val endDate: LocalDateTime,
//    val lectureDates: List<com.msg.model.remote.response.lecture.LectureDates>,
//    val lectureType: LectureType,
//    val lectureStatus: LectureStatus,
//    val headCount: Int,
//    val maxRegisteredUser: Int,
//    val isRegistered: Boolean,
//    val lecturer: String,
//    val credit: Int,
    var selectedLectureId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    var name = mutableStateOf("")
        private set

    var content = mutableStateOf("")
        private set

    var lecturer = mutableStateOf("")
        private set

    var credit = mutableIntStateOf(0)
        private set

    var headCount = mutableIntStateOf(0)
        private set

    var maxRegisteredUser = mutableIntStateOf(0)
        private set

    var semester = mutableStateOf(Semester.SECOND_YEAR_FALL_SEMESTER)
        private set

    var division = mutableStateOf(Division.AI_CONVERGENCE_AI)
        private set

    var department = mutableStateOf("")
        private set

    var line = mutableStateOf("")
        private set

    var userId = mutableStateOf(UUID.randomUUID())
        private set

    var lectureType = mutableStateOf(LectureType.UNIVERSITY_EXPLORATION_PROGRAM)
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

    var lectureDate = mutableStateListOf(completeDate, startTime, endTime)
        private set

    fun getLectureList(
        page: Int,
        size: Int,
        type: LectureType,
    ) = viewModelScope.launch {
        getLectureListUseCase(
            page = page,
            size = size,
            type = type
        ).onSuccess {
            it.catch { remoteError ->
                _getLectureListResponse.value = remoteError.errorHandling()
                Log.e("remoteError", remoteError.toString())
            }.collect { response ->
                _getLectureListResponse.value = Event.Success(data = response)
                Log.e("viewModel 단 response", response.toString())
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
        completeDate: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
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
                lectureDates = listOf(
                    LectureDates(
                        completeDate = completeDate,
                        startTime = startTime,
                        endTime = endTime
                    )
                ),
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
//    }
    }
}