package com.msg.club

import Authority
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.club.util.Event
import com.msg.club.util.errorHandling
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.domain.club.GetClubDetailUseCase
import com.msg.domain.club.GetClubListUseCase
import com.msg.domain.club.GetMyClubDetailUseCase
import com.msg.domain.club.GetStudentBelongClubUseCase
import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val getClubDetailUseCase: GetClubDetailUseCase,
    private val getClubListUseCase: GetClubListUseCase,
    private val getMyClubDetailUseCase: GetMyClubDetailUseCase,
    private val getStudentBelongClubUseCase: GetStudentBelongClubUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {

    private val _getClubDetailResponse = MutableStateFlow<Event<ClubDetailResponse>>(Event.Loading)
    val getClubDetailResponse = _getClubDetailResponse.asStateFlow()

    private val _getClubListResponse =
        MutableStateFlow<Event<List<ClubListResponse>>>(Event.Loading)
    val getClubListResponse = _getClubListResponse.asStateFlow()

    private val _getMyClubDetailResponse =
        MutableStateFlow<Event<ClubDetailResponse>>(Event.Loading)
    val getMyClubDetailResponse = _getMyClubDetailResponse.asStateFlow()

    private val _getStudentBelongClubResponse =
        MutableStateFlow<Event<StudentBelongClubResponse>>(Event.Loading)
    val getStudentBelongClubResponse = _getStudentBelongClubResponse.asStateFlow()

    val role = getRole().toString()

    var detailClub = mutableStateOf(
        ClubDetailResponse(
            clubId = 0,
            clubName = "",
            highSchoolName = "",
            headCount = 0,
            students = listOf(),
            teacher = ClubDetailResponse.Teacher(
                id = UUID.randomUUID(),
                name = ""
            )
        )
    )
        private set

    var clubList = mutableStateListOf<ClubListResponse>()
        private set

    var studentBelongClub = mutableStateOf(
        StudentBelongClubResponse(
            name = "",
            phoneNumber = "",
            email = "",
            credit = 0
        )
    )
        private set

    var selectedClubId = mutableLongStateOf(0)
        private set

    fun getClubDetail() = viewModelScope.launch {
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

    fun getClubList(
        highSchool: HighSchool,
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

    fun getMyClubDetail() = viewModelScope.launch {
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

    fun getStudentBelongClub(
        id: Long,
        studentId: UUID,
    ) = viewModelScope.launch {
        getStudentBelongClubUseCase(
            id = id,
            studentId = studentId
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentBelongClubResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentBelongClubResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentBelongClubResponse.value = error.errorHandling()
        }
    }


    private fun getRole(): Authority = runBlocking {
        return@runBlocking authTokenDataSource.getAuthority().first()
    }
}
