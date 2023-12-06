package com.msg.student_activity.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.activity.AddStudentActivityInfoUseCase
import com.msg.domain.activity.ApproveStudentActivityInfoUseCase
import com.msg.domain.activity.DeleteStudentActivityInfoUseCase
import com.msg.domain.activity.EditStudentActivityInfoUseCase
import com.msg.domain.activity.InquiryDetailStudentActivityInfoUseCase
import com.msg.domain.activity.InquiryEntireStudentActivityInfoListUseCase
import com.msg.domain.activity.InquiryMyStudentActivityInfoListUseCase
import com.msg.domain.activity.InquiryStudentActivityInfoListUseCase
import com.msg.domain.activity.RejectStudentActivityInfoUseCase
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.model.activity.InquiryStudentActivityModel
import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import com.msg.student_activity.util.Event
import com.msg.student_activity.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class StudentActivityViewModel @Inject constructor(
    private val inquiryMyStudentActivityInfoListUseCase: InquiryMyStudentActivityInfoListUseCase,
    private val inquiryStudentActivityInfoListUseCase: InquiryStudentActivityInfoListUseCase,
    private val inquiryEntireStudentActivityInfoListUseCase: InquiryEntireStudentActivityInfoListUseCase,
    private val inquiryDetailStudentActivityInfoUseCase: InquiryDetailStudentActivityInfoUseCase,
    private val addStudentActivityInfoUseCase: AddStudentActivityInfoUseCase,
    private val editStudentActivityInfoUseCase: EditStudentActivityInfoUseCase,
    private val approveStudentActivityInfoUseCase: ApproveStudentActivityInfoUseCase,
    private val rejectStudentActivityInfoUseCase: RejectStudentActivityInfoUseCase,
    private val deleteStudentActivityInfoUseCase: DeleteStudentActivityInfoUseCase,
    private val authTokenDataSource: AuthTokenDataSource
) : ViewModel() {

    val role = Authority.valueOf(authTokenDataSource.getAuthority().toString())

    private val _getStudentActivityListResponse = MutableStateFlow<Event<InquiryStudentActivityListResponse>>(Event.Loading)
    val getStudentActivityListResponse = _getStudentActivityListResponse.asStateFlow()

    private val _getDetailStudentActivityResponse = MutableStateFlow<Event<InquiryDetailStudentActivityInfoResponse>>(Event.Loading)
    val getDetailStudentActivityResponse = _getDetailStudentActivityResponse.asStateFlow()

    private val _addStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val addStudentActivityResponse = _addStudentActivityResponse.asStateFlow()

    private val _approveStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val approveStudentActivityResponse = _approveStudentActivityResponse.asStateFlow()

    private val _rejectStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val rejectStudentActivityResponse = _rejectStudentActivityResponse.asStateFlow()

    private val _deleteStudentActivityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteStudentActivityResponse = _deleteStudentActivityResponse.asStateFlow()

    var studentActivityList = mutableStateListOf<InquiryStudentActivityModel>()
        private set

    var studentDetailActivityData = mutableStateOf(
        InquiryDetailStudentActivityInfoResponse(
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

    fun inquiryStudentActivityList(role: Authority, page: Int, size: Int, sort: String, id: UUID? = null) = viewModelScope.launch {
        when(role) {
            Authority.ROLE_STUDENT -> {
                inquiryMyStudentActivityInfoListUseCase(
                    page = page,
                    size = size,
                    sort = sort
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
                    inquiryStudentActivityInfoListUseCase(
                        page = page,
                        size = size,
                        sort = sort,
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
                inquiryEntireStudentActivityInfoListUseCase(
                    page = page,
                    size = size,
                    sort = sort
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

    fun inquiryDetailStudentActivity(
        id: UUID
    ) = viewModelScope.launch {
        inquiryDetailStudentActivityInfoUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _getDetailStudentActivityResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getDetailStudentActivityResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getDetailStudentActivityResponse.value = error.errorHandling()
        }
    }

    fun addActivityInfo(
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

    fun editActivityInfo(
        id: UUID,
        title: String,
        content: String,
        credit: Int,
        activityDate: LocalDate
    ) = viewModelScope.launch {
        editStudentActivityInfoUseCase(
            id = id,
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

    fun approveActivityInfo(
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

    fun rejectActivityInfo(
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

    fun deleteActivityInfo(
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

    fun setSelectedActivityId(id: UUID) {}
}