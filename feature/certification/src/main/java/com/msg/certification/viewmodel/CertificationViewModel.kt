package com.msg.certification.viewmodel

import Authority
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.certification.util.Event
import com.msg.certification.util.errorHandling
import com.msg.data.repository.auth.AuthRepository
import com.msg.domain.certification.EditCertificationUseCase
import com.msg.domain.certification.GetCertificationListForStudentUseCase
import com.msg.domain.certification.GetCertificationListForTeacherUseCase
import com.msg.domain.certification.WriteCertificationUseCase
import com.msg.domain.club.GetStudentBelongClubUseCase
import com.msg.domain.lecture.GetLectureSignUpHistoryUseCase
import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import com.msg.model.remote.response.lecture.GetLectureSignUpHistoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class CertificationViewModel @Inject constructor(
    private val getCertificationListForTeacherUseCase: GetCertificationListForTeacherUseCase,
    private val getCertificationListForStudentUseCase: GetCertificationListForStudentUseCase,
    private val writeCertificationUseCase: WriteCertificationUseCase,
    private val editCertificationUseCase: EditCertificationUseCase,
    private val getStudentBelongClubUseCase: GetStudentBelongClubUseCase,
    private val getLectureSignUpHistoryUseCase: GetLectureSignUpHistoryUseCase,
    private val authRepository: AuthRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getCertificationListResponse = MutableStateFlow<Event<List<CertificationListResponse>>>(Event.Loading)
    val getCertificationListResponse = _getCertificationListResponse.asStateFlow()

    private val _writeCertificationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val writeCertificationResponse = _writeCertificationResponse.asStateFlow()

    private val _editCertificationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val editCertificationResponse = _editCertificationResponse.asStateFlow()

    private val _getStudentBelongResponse = MutableStateFlow<Event<StudentBelongClubResponse>>(Event.Loading)
    val getStudentBelongResponse = _getStudentBelongResponse.asStateFlow()

    private val _getLectureSignUpHistoryResponse = MutableStateFlow<Event<GetLectureSignUpHistoryResponse>>(Event.Loading)
    val getLectureSignUpHistoryResponse = _getLectureSignUpHistoryResponse.asStateFlow()

    private val studentId = UUID.fromString(savedStateHandle.get<String>("studentId"))

    private val clubId: Long? = savedStateHandle.get<Long>("clubId")

    var selectedCertificationId = mutableStateOf<UUID?>(null)
        private set

    var selectedTitle = mutableStateOf("")
        private set

    var selectedDate = mutableStateOf<LocalDate?>(null)
        private set

    var certificationList = mutableStateListOf<CertificationListResponse>()
        private set

    var studentData = mutableStateOf(
        StudentBelongClubResponse(
            name = "",
            phoneNumber = "",
            email = "",
            credit = 0
        )
    )
        private set

    var lectureData = mutableStateOf(
        GetLectureSignUpHistoryResponse(
            lectures = listOf()
        )
    )
        private set

    internal fun getCertificationList() = viewModelScope.launch {
        if (getRole() == Authority.ROLE_STUDENT) {
            getCertificationListForStudentUseCase().onSuccess {
                it.catch { remoteError ->
                    _getCertificationListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getCertificationListResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getCertificationListResponse.value = error.errorHandling()
            }
        } else {
            if (studentId != null) {
                getCertificationListForTeacherUseCase(studentId).onSuccess {
                    it.catch { remoteError ->
                        _getCertificationListResponse.value = remoteError.errorHandling()
                    }.collect { response ->
                        _getCertificationListResponse.value = Event.Success(data = response)
                    }
                }.onFailure { error ->
                    _getCertificationListResponse.value = error.errorHandling()
                }
            }
        }
    }

    internal fun writeCertification(
        name: String,
        acquisitionDate: LocalDate
    ) = viewModelScope.launch {
        writeCertificationUseCase(
            body = WriteCertificationRequest(
                name = name,
                acquisitionDate = acquisitionDate
            )
        ).onSuccess {
            it.catch { remoteError ->
                _writeCertificationResponse.value = remoteError.errorHandling()
            }.collect {
                _writeCertificationResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _writeCertificationResponse.value = error.errorHandling()
        }
    }

    internal fun editCertification(
        name: String,
        acquisitionDate: LocalDate
    ) = viewModelScope.launch {
        editCertificationUseCase(
            id = selectedCertificationId.value!!,
            body = WriteCertificationRequest(
                name = name,
                acquisitionDate = acquisitionDate
            )
        ).onSuccess {
            it.catch { remoteError ->
                _editCertificationResponse.value = remoteError.errorHandling()
            }.collect {
                _editCertificationResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _editCertificationResponse.value = error.errorHandling()
        }
    }

    internal fun getStudentBelong() = viewModelScope.launch {
        if (clubId != null && studentId != null) {
            getStudentBelongClubUseCase(
                id = clubId,
                studentId = studentId
            ).onSuccess {
                it.catch { remoteError ->
                    _getStudentBelongResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getStudentBelongResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getStudentBelongResponse.value = error.errorHandling()
            }
        }
    }

    internal fun getLectureSignUpHistory() = viewModelScope.launch {
        if (studentId != null) {
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
    }

    private fun getRole(): Authority = runBlocking {
        return@runBlocking authRepository.getAuthority().first()
    }
}