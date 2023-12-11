package com.msg.lecture.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.lecture.util.Event
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.lecture.ApprovePendingLectureUseCase
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.LectureApplicationUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.domain.lecture.RejectPendingLectureUseCase
import com.msg.lecture.util.errorHandling
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.request.lecture.OpenLectureRequest
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val approvePendingLectureUseCase: ApprovePendingLectureUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val getLectureListUseCase: GetLectureListUseCase,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val openLectureUseCase: OpenLectureUseCase,
    private val rejectPendingLectureUseCase: RejectPendingLectureUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {

    val role = Authority.valueOf(authTokenDataSource.getAuthority().toString())

    private val _getLectureListResponse =
        MutableStateFlow<Event<List<LectureListResponse>>>(Event.Loading)
    val getLectureListResponse = _getLectureListResponse.asStateFlow()

    private val _getDetailLectureResponse =
        MutableStateFlow<Event<DetailLectureResponse>>(Event.Loading)
    val getDetailLectureResponse = _getDetailLectureResponse.asStateFlow()

    private val _openLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val openLectureResponse = _openLectureResponse.asStateFlow()

    private val _approvePendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val approvePendingLectureResponse = _approvePendingLectureResponse.asStateFlow()

    private val _rejectPendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val rejectPendingLectureResponse = _rejectPendingLectureResponse

    private val _lectureApplicationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationResponse = _lectureApplicationResponse.asStateFlow()

    var lectureList = mutableStateListOf<LectureListResponse>()
        private set

    fun getLectureList(
        role: Authority,
        page: Int,
        size: Int,
        status: ApproveStatus,
        type: LectureType,
    ) = viewModelScope.launch {
        when (role) {
            Authority.ROLE_ADMIN -> {
                getLectureListUseCase(
                    page = page,
                    size = size,
                    status = status,
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

            else -> {
                getLectureListUseCase(
                    page = page,
                    size = size,
                    status = status,
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
        }
    }

    fun getDetailLecture(
        id: UUID,
    ) = viewModelScope.launch {
        getDetailLectureUseCase(id = id).onSuccess {
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
        openLectureUseCase(
            OpenLectureRequest(
                name = name,
                content = content,
                startDate = startDate,
                endDate = endDate,
                completeDate = completeDate,
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
}