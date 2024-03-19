package com.msg.lecture.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.lecture.util.Event
import com.msg.lecture.util.authorityOf
import com.msg.lecture.util.errorHandling
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureStatus
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.ContentArray
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import com.msg.model.remote.response.lecture.Lectures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
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

    private val _approvePendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val approvePendingLectureResponse = _approvePendingLectureResponse.asStateFlow()

    private val _rejectPendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val rejectPendingLectureResponse = _rejectPendingLectureResponse.asStateFlow()

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

//    var lectureDetailData = mutableStateOf(
//        DetailLectureResponse(
//            id = UUID.randomUUID(),
//            name = "",
//            startDate = current,
//            endDate = current,
//            completeDate = current,
//            lectureType = LectureType.UNIVERSITY_EXPLORATION_PROGRAM,
//            lectureStatus = LectureStatus.OPEN,
//            headCount = 0,
//            maxRegisteredUser = 0,
//            isRegistered = true,
//            lecturer = "",
//            credit = 0
//        )
//    )
//        private set

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

    var completeDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var startDate = mutableStateOf<LocalDateTime?>(null)
        private set

    var endDate = mutableStateOf<LocalDateTime?>(null)
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
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        completeDate: LocalDateTime,
        lectureType: LectureType,
        credit: Int,
        maxRegisteredUser: Int,
    ) = viewModelScope.launch {
//        openLectureUseCase(
//            OpenLectureRequest(
//                name = name,
//                content = content,
//                startDate = startDate,
//                endDate = endDate,
//                completeDate = completeDate,
//                lectureType = lectureType,
//                credit = credit,
//                maxRegisteredUser = maxRegisteredUser
//            )
//        ).onSuccess {
//            it.catch { remoteError ->
//                _openLectureResponse.value = remoteError.errorHandling()
//            }.collect {
//                _openLectureResponse.value = Event.Success()
//            }
//        }.onFailure { error ->
//            _openLectureResponse.value = error.errorHandling()
//        }
//    }
    }
}