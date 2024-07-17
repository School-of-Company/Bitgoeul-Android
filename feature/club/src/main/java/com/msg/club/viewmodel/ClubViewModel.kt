package com.msg.club.viewmodel

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.GetAuthorityUseCase
import com.msg.domain.usecase.club.GetClubDetailUseCase
import com.msg.domain.usecase.club.GetClubListUseCase
import com.msg.domain.usecase.club.GetMyClubDetailUseCase
import com.msg.domain.usecase.club.GetStudentBelongClubUseCase
import com.msg.model.entity.club.ClubDetailEntity
import com.msg.model.entity.club.ClubListEntity
import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.model.enumdata.HighSchool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val getClubDetailUseCase: GetClubDetailUseCase,
    private val getClubListUseCase: GetClubListUseCase,
    private val getMyClubDetailUseCase: GetMyClubDetailUseCase,
    private val getStudentBelongClubUseCase: GetStudentBelongClubUseCase,
    private val getAuthorityUseCase: GetAuthorityUseCase,
) : ViewModel() {

    private val _getClubDetailResponse = MutableStateFlow<Event<ClubDetailEntity>>(Event.Loading)
    val getClubDetailResponse = _getClubDetailResponse.asStateFlow()

    private val _getClubListResponse =
        MutableStateFlow<Event<List<ClubListEntity>>>(Event.Loading)
    val getClubListResponse = _getClubListResponse.asStateFlow()

    private val _getMyClubDetailResponse = MutableStateFlow<Event<ClubDetailEntity>>(Event.Loading)
    val getMyClubDetailResponse = _getMyClubDetailResponse.asStateFlow()

    private val _getStudentBelongClubResponse =
        MutableStateFlow<Event<StudentBelongClubEntity>>(Event.Loading)
    val getStudentBelongClubResponse = _getStudentBelongClubResponse.asStateFlow()

    val role = getRole().toString()

    var detailClub = mutableStateOf(
        ClubDetailEntity(
            clubId = 0,
            clubName = "",
            highSchoolName = "",
            headCount = 0,
            students = listOf(),
            teacher = ClubDetailEntity.Teacher(
                id = UUID.randomUUID(),
                name = ""
            )
        )
    )
        private set

    var clubList = mutableStateListOf<ClubListEntity>()
        private set

    var studentBelongClub = mutableStateOf(
        StudentBelongClubEntity(
            name = "",
            phoneNumber = "",
            email = "",
            credit = 0
        )
    )
        private set

    var selectedClubId = mutableLongStateOf(0)
        private set

    internal fun getClubDetail() = viewModelScope.launch {
        getClubDetailUseCase(id = selectedClubId.longValue).onSuccess {
            it.catch { remoteError ->
                _getClubDetailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getClubDetailResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getClubDetailResponse.value = error.errorHandling()
        }
    }

    internal fun getClubList(
        highSchool: String,
    ) = viewModelScope.launch {
        getClubListUseCase(highSchool = highSchool).onSuccess {
            it.catch { remoteError ->
                _getClubListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getClubListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getClubListResponse.value = error.errorHandling()
        }
    }

    internal fun getMyClubDetail() = viewModelScope.launch {
        getMyClubDetailUseCase().onSuccess {
            it.catch { remoteError ->
                _getMyClubDetailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getMyClubDetailResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getMyClubDetailResponse.value = error.errorHandling()
        }
    }

    private fun getRole() = viewModelScope.launch {
        getAuthorityUseCase()
    }
}
