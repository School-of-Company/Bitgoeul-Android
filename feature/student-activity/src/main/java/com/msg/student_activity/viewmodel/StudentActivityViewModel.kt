package com.msg.student_activity.viewmodel

import Authority
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.data.repository.auth.AuthRepository
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.domain.activity.AddStudentActivityInfoUseCase
import com.msg.domain.activity.ApproveStudentActivityInfoUseCase
import com.msg.domain.activity.DeleteStudentActivityInfoUseCase
import com.msg.domain.activity.EditStudentActivityInfoUseCase
import com.msg.domain.activity.GetDetailStudentActivityInfoUseCase
import com.msg.domain.activity.GetEntireStudentActivityInfoListUseCase
import com.msg.domain.activity.GetMyStudentActivityInfoListUseCase
import com.msg.domain.activity.GetStudentActivityInfoListUseCase
import com.msg.domain.activity.RejectStudentActivityInfoUseCase
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.model.activity.GetStudentActivityModel
import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.GetDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.GetStudentActivityListResponse
import com.msg.student_activity.util.Event
import com.msg.student_activity.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class StudentActivityViewModel @Inject constructor(
    private val getMyStudentActivityInfoListUseCase: GetMyStudentActivityInfoListUseCase,
    private val getStudentActivityInfoListUseCase: GetStudentActivityInfoListUseCase,
    private val getEntireStudentActivityInfoListUseCase: GetEntireStudentActivityInfoListUseCase,
    private val getDetailStudentActivityInfoUseCase: GetDetailStudentActivityInfoUseCase,
    private val addStudentActivityInfoUseCase: AddStudentActivityInfoUseCase,
    private val editStudentActivityInfoUseCase: EditStudentActivityInfoUseCase,
    private val approveStudentActivityInfoUseCase: ApproveStudentActivityInfoUseCase,
    private val rejectStudentActivityInfoUseCase: RejectStudentActivityInfoUseCase,
    private val deleteStudentActivityInfoUseCase: DeleteStudentActivityInfoUseCase,
    private val authRepository: AuthRepository,
) : ViewModel() {

    val role = getRole().toString()

    private val _getStudentActivityListResponse = MutableStateFlow<Event<GetStudentActivityListResponse>>(Event.Loading)
    val getStudentActivityListResponse = _getStudentActivityListResponse.asStateFlow()

    private val _getDetailStudentActivityResponse = MutableStateFlow<Event<GetDetailStudentActivityInfoResponse>>(Event.Loading)
    val getDetailStudentActivityResponse = _getDetailStudentActivityResponse.asStateFlow()

    private val _addStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val addStudentActivityResponse = _addStudentActivityResponse.asStateFlow()

    private val _approveStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val approveStudentActivityResponse = _approveStudentActivityResponse.asStateFlow()

    private val _rejectStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val rejectStudentActivityResponse = _rejectStudentActivityResponse.asStateFlow()

    private val _deleteStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteStudentActivityResponse = _deleteStudentActivityResponse.asStateFlow()

    var studentActivityList = mutableStateListOf<GetStudentActivityModel>()
        private set

    var studentDetailActivityData = mutableStateOf(
        GetDetailStudentActivityInfoResponse(
            id = UUID.randomUUID(),
            title = "",
            content = "",
            credit = 0,
            activityDate = LocalDate.now(),
            modifiedAt = LocalDateTime.now(),
            approveState = ApproveStatus.PENDING
        )
    )
        private set

    var selectedActivityId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    var title = mutableStateOf("")
        private set

    var content = mutableStateOf("")
        private set

    var credit = mutableIntStateOf(0)
        private set

    var activityDate = mutableStateOf<LocalDate?>(null)
        private set

    var detailState = mutableStateOf(false)
        private set

    internal fun getStudentActivityList(role: Authority, page: Int, size: Int, sort: String, id: UUID? = null) = viewModelScope.launch {
        when(role) {
            Authority.ROLE_STUDENT -> {
                getMyStudentActivityInfoListUseCase(
                    page = page,
                    size = size,
                ).onSuccess {
                    it.catch { remoteError ->
                        _getStudentActivityListResponse.value = remoteError.errorHandling()
                    }.collect { response ->
                        _getStudentActivityListResponse.value = Event.Success(data = response)
                    }
                }.onFailure { error ->
                    _getStudentActivityListResponse.value = error.errorHandling()
                }
            }
            Authority.ROLE_TEACHER -> {
                if (id != null) {
                    getStudentActivityInfoListUseCase(
                        page = page,
                        size = size,
                        id = id
                    ).onSuccess {
                        it.catch { remoteError ->
                            _getStudentActivityListResponse.value = remoteError.errorHandling()
                        }.collect { response ->
                            _getStudentActivityListResponse.value = Event.Success(data = response)
                        }
                    }.onFailure { error ->
                        _getStudentActivityListResponse.value = error.errorHandling()
                    }
                }
            }
            Authority.ROLE_ADMIN -> {
                getEntireStudentActivityInfoListUseCase(
                    page = page,
                    size = size,
                ).onSuccess {
                    it.catch { remoteError ->
                        _getStudentActivityListResponse.value = remoteError.errorHandling()
                    }.collect { response ->
                        _getStudentActivityListResponse.value = Event.Success(data = response)
                    }
                }.onFailure { error ->
                    _getStudentActivityListResponse.value = error.errorHandling()
                }
            }

            else -> {}
        }
    }

    internal fun getDetailStudentActivity(
        id: UUID
    ) = viewModelScope.launch {
        getDetailStudentActivityInfoUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _getDetailStudentActivityResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getDetailStudentActivityResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getDetailStudentActivityResponse.value = error.errorHandling()
        }
    }

    internal fun addActivityInfo(
        title: String,
        content: String,
        credit: Int,
        activityDate: LocalDate
    ) = viewModelScope.launch {
        addStudentActivityInfoUseCase(
            StudentActivityModel(
                title = title,
                content = content,
                credit = credit,
                activityDate = activityDate
            )
        ).onSuccess {
            it.catch { remoteError ->
                _addStudentActivityResponse.value = remoteError.errorHandling()
            }.collect {
                _addStudentActivityResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _addStudentActivityResponse.value = error.errorHandling()
        }
    }

    internal fun approveActivityInfo(
        id: UUID
    ) = viewModelScope.launch {
        approveStudentActivityInfoUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _approveStudentActivityResponse.value = remoteError.errorHandling()
            }.collect {
                _approveStudentActivityResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _approveStudentActivityResponse.value = error.errorHandling()
        }
    }

    internal fun rejectActivityInfo(
        id: UUID
    ) = viewModelScope.launch {
        rejectStudentActivityInfoUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _rejectStudentActivityResponse.value = remoteError.errorHandling()
            }.collect {
                _rejectStudentActivityResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _rejectStudentActivityResponse.value = error.errorHandling()
        }
    }

    internal fun deleteActivityInfo(
        id: UUID
    ) = viewModelScope.launch {
        deleteStudentActivityInfoUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _deleteStudentActivityResponse.value = remoteError.errorHandling()
            }.collect {
                _rejectStudentActivityResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _deleteStudentActivityResponse.value = error.errorHandling()
        }
    }

    private fun getRole(): Authority = runBlocking {
        return@runBlocking authRepository.getAuthority().first()
    }
}