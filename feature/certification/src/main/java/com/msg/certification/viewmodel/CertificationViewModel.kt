package com.msg.certification.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.certification.viewmodel.uistate.EditCertificationUiState
import com.msg.certification.viewmodel.uistate.GetCertificationListUiState
import com.msg.certification.viewmodel.uistate.GetLectureSignUpHistoryUiState
import com.msg.certification.viewmodel.uistate.GetStudentBelongClubDetailUiState
import com.msg.certification.viewmodel.uistate.WriteCertificationUiState
import com.msg.common.event.Event
import com.msg.common.errorhandling.errorHandling
import com.msg.common.result.Result
import com.msg.common.result.asResult
import com.msg.domain.usecase.auth.GetAuthorityUseCase
import com.msg.domain.usecase.certification.*
import com.msg.domain.usecase.certification.GetCertificationListUseCase
import com.msg.domain.usecase.certification.WriteCertificationUseCase
import com.msg.domain.usecase.club.GetStudentBelongClubUseCase
import com.msg.domain.usecase.lecture.GetLectureSignUpHistoryUseCase
import com.msg.model.entity.certification.CertificationListEntity
import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity
import com.msg.model.param.certification.WriteCertificationParam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class CertificationViewModel @Inject constructor(
    private val getCertificationListUseCase: GetCertificationListUseCase,
    private val writeCertificationUseCase: WriteCertificationUseCase,
    private val editCertificationUseCase: EditCertificationUseCase,
    private val getStudentBelongClubUseCase: GetStudentBelongClubUseCase,
    private val getLectureSignUpHistoryUseCase: GetLectureSignUpHistoryUseCase,
    private val getAuthorityUseCase: GetAuthorityUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val SELECTED_TITLE = "selectedTitle"
    }

    private val role = getRole().toString()

    private val _getCertificationListUiState = MutableStateFlow<GetCertificationListUiState>(GetCertificationListUiState.Loading)
    val getCertificationListUiState: StateFlow<GetCertificationListUiState> = _getCertificationListUiState.asStateFlow()

    private val _getLectureSignUpHistoryUiState = MutableStateFlow<GetLectureSignUpHistoryUiState>(GetLectureSignUpHistoryUiState.Loading)
    val getLectureSignUpHistoryUiState: StateFlow<GetLectureSignUpHistoryUiState> = _getLectureSignUpHistoryUiState.asStateFlow()

    private val _writeCertificationUiState = MutableStateFlow<WriteCertificationUiState>(WriteCertificationUiState.Success)
    val writeCertificationUiState: StateFlow<WriteCertificationUiState> = _writeCertificationUiState.asStateFlow()

    private val _editCertificationUiState = MutableStateFlow<EditCertificationUiState>(EditCertificationUiState.Success)
    val editCertificationUiState: StateFlow<EditCertificationUiState> = _editCertificationUiState.asStateFlow()

    private val _getStudentBelongClubDetailUiState = MutableStateFlow<GetStudentBelongClubDetailUiState>(GetStudentBelongClubDetailUiState.Loading)
    val getStudentBelongClubDetailUiState: StateFlow<GetStudentBelongClubDetailUiState> = _getStudentBelongClubDetailUiState.asStateFlow()

    private val _getCertificationListResponse = MutableStateFlow<Event<List<CertificationListEntity>>>(Event.Loading)
    val getCertificationListResponse = _getCertificationListResponse.asStateFlow()

    private val _writeCertificationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val writeCertificationResponse = _writeCertificationResponse.asStateFlow()

    private val _editCertificationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val editCertificationResponse = _editCertificationResponse.asStateFlow()

    private val _getStudentBelongResponse = MutableStateFlow<Event<StudentBelongClubEntity>>(Event.Loading)
    val getStudentBelongResponse = _getStudentBelongResponse.asStateFlow()

    private val _getLectureSignUpHistoryResponse = MutableStateFlow<Event<GetLectureSignUpHistoryEntity>>(Event.Loading)
    val getLectureSignUpHistoryResponse = _getLectureSignUpHistoryResponse.asStateFlow()

    private val studentId = UUID.fromString(savedStateHandle.get<String>("studentId"))

    private val clubId: Long? = savedStateHandle.get<Long>("clubId")

    var selectedCertificationId = mutableStateOf<UUID?>(null)
        private set

    internal var selectedTitle =
        savedStateHandle.getStateFlow(key = SELECTED_TITLE, initialValue = "")

    var selectedDate = mutableStateOf<LocalDate?>(null)
        private set

    var certificationList = mutableStateListOf<CertificationListEntity>()
        private set

    var studentData = mutableStateOf(
        StudentBelongClubEntity(
            name = "",
            phoneNumber = "",
            email = "",
            credit = 0
        )
    )
        private set

    var lectureData = mutableStateOf(
        GetLectureSignUpHistoryEntity(
            lectures = listOf()
        )
    )
        private set

    internal fun getCertificationList() = viewModelScope.launch {
        getCertificationListUseCase(role = role, studentId = studentId)
            .asResult()
            .collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        _getCertificationListUiState.value = GetCertificationListUiState.Loading
                    }
                    is Result.Success -> {
                        if (result.data.isEmpty()) {
                            _getCertificationListUiState.value = GetCertificationListUiState.Empty
                        } else {
                            _getCertificationListUiState.value = GetCertificationListUiState.Success(result.data) }
                    }
                    is Result.Error -> { _getCertificationListUiState.value = GetCertificationListUiState.Error(result.exception) }
                }
            }
    }

    internal fun writeCertification(
        name: String,
        acquisitionDate: LocalDate
    ) = viewModelScope.launch {
        writeCertificationUseCase(
            body = WriteCertificationParam(
                name = name,
                acquisitionDate = acquisitionDate
            )
        )
            .asResult()
            .collectLatest { result ->
                when (result) {
                    is Result.Success -> { _writeCertificationUiState.value = WriteCertificationUiState.Success }
                    is Result.Error -> { _writeCertificationUiState.value = WriteCertificationUiState.Error(result.exception) }
                    else -> {}
                }
            }
    }

    internal fun editCertification(
        name: String,
        acquisitionDate: LocalDate
    ) = viewModelScope.launch {
        editCertificationUseCase(
            id = selectedCertificationId.value!!,
            body = WriteCertificationParam(
                name = name,
                acquisitionDate = acquisitionDate
            )
        )
            .asResult()
            .collectLatest { result ->
                when (result) {
                    is Result.Success -> { _editCertificationUiState.value = EditCertificationUiState.Success }
                    is Result.Error -> { _editCertificationUiState.value = EditCertificationUiState.Error(result.exception) }
                    else -> {}
                }
            }
    }

    internal fun getStudentBelong() = viewModelScope.launch {
        if (clubId != null && studentId != null) {
            getStudentBelongClubUseCase(
                id = clubId,
                studentId = studentId
            )
                .asResult()
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> { _getStudentBelongClubDetailUiState.value = GetStudentBelongClubDetailUiState.Loading }
                        is Result.Success -> { _getStudentBelongClubDetailUiState.value = GetStudentBelongClubDetailUiState.Success(result.data) }
                        is Result.Error -> { _getStudentBelongClubDetailUiState.value = GetStudentBelongClubDetailUiState.Error(result.exception) }
                    }
                }
        }
    }

    internal fun getLectureSignUpHistory() = viewModelScope.launch {
        if (studentId != null) {
            getLectureSignUpHistoryUseCase(studentId = studentId)
                .asResult()
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> { _getLectureSignUpHistoryUiState.value = GetLectureSignUpHistoryUiState.Loading }
                        is Result.Success -> { _getLectureSignUpHistoryUiState.value = GetLectureSignUpHistoryUiState.Success(result.data) }
                        is Result.Error -> { _getLectureSignUpHistoryUiState.value = GetLectureSignUpHistoryUiState.Error(result.exception) }
                    }
                }
        }
    }

    private fun getRole() = viewModelScope.launch {
        getAuthorityUseCase()
    }

    internal fun onSelectedTitleChange(value: String) {
        savedStateHandle[SELECTED_TITLE] = value
    }
}