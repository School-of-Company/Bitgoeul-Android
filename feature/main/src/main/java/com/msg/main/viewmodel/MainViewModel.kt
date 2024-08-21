package com.msg.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.GetAuthorityUseCase
import com.msg.domain.usecase.company.GetCompanyListUseCase
import com.msg.domain.usecase.government.GetGovernmentUseCase
import com.msg.domain.usecase.school.GetSchoolListUseCase
import com.msg.domain.usecase.university.GetUniversityUseCase
import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.entity.university.GetUniversityEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.msg.domain.usecase.faq.AddFrequentlyAskedQuestionUseCase as AddFAQUseCase
import com.msg.domain.usecase.faq.GetFrequentlyAskedQuestionsListUseCase as GetFAQUseCase
import com.msg.model.param.faq.AddFrequentlyAskedQuestionsParam as AddFAQRequest
import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity as GetFAQDetailEntity

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addFAQUseCase: AddFAQUseCase,
    private val getFAQUseCase: GetFAQUseCase,
    private val getSchoolListUseCase: GetSchoolListUseCase,
    private val getUniversityListUseCase: GetUniversityUseCase,
    private val getCompanyListUseCase: GetCompanyListUseCase,
    private val getGovernmentListUseCase: GetGovernmentUseCase,
    private val getAuthorityUseCase: GetAuthorityUseCase,
) : ViewModel() {

    val role = getRole().toString()

    private val _addFaqResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val addFaqResponse = _addFaqResponse.asStateFlow()

    private val _getFaqListResponse = MutableStateFlow<Event<List<GetFAQDetailEntity>>>(Event.Loading)
    val getFaqListResponse = _getFaqListResponse.asStateFlow()

    private val _getSchoolListResponse = MutableStateFlow<Event<GetSchoolListEntity>>(Event.Loading)
    val getSchoolListResponse = _getSchoolListResponse.asStateFlow()

    private val _getUniversityListResponse = MutableStateFlow<Event<GetUniversityEntity>>(Event.Loading)
    val getUniversityListResponse = _getUniversityListResponse.asStateFlow()

    private val _getCompanyListResponse = MutableStateFlow<Event<GetCompanyListEntity>>(Event.Loading)
    val getCompanyListResponse = _getCompanyListResponse.asStateFlow()

    private val _getGovernmentListResponse = MutableStateFlow<Event<GetGovernmentEntity>>(Event.Loading)
    val getGovernmentListResponse = _getGovernmentListResponse.asStateFlow()

    var faqList = mutableStateListOf<GetFAQDetailEntity>()
        private set

    private var errorCode: Int = 200

    var highSchoolList = mutableStateOf(
        GetSchoolListEntity(
            schools = listOf()
        )
    )
        private set

    var universityList = mutableStateOf(
        GetUniversityEntity(
            universities = listOf()
        )
    )
        private set

    var companyList = mutableStateOf(
        GetCompanyListEntity(
            companies = listOf()
        )
    )
        private set

    var governmentList = mutableStateOf(
        GetGovernmentEntity(
            governments = listOf()
        )
    )
        private set

    internal fun addFaq(
        question: String,
        answer: String
    ) = viewModelScope.launch {
        addFAQUseCase(
            body = AddFAQRequest(
                question = question,
                answer = answer
            )
        ).onSuccess {
            it.catch { remoteError ->
                _addFaqResponse.value = remoteError.errorHandling()
                errorCode = remoteError.hashCode()
            }.collect {
                _addFaqResponse.value = Event.Success()
                errorCode = 200
            }
        }.onFailure { error ->
            _addFaqResponse.value = error.errorHandling()
            errorCode = error.hashCode()
        }
    }

    internal fun getFaq() = viewModelScope.launch {
        getFAQUseCase().onSuccess {
            it.catch { remoteError ->
                _getFaqListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getFaqListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getFaqListResponse.value = error.errorHandling()
        }
    }

    internal fun getSchoolList() = viewModelScope.launch {
        getSchoolListUseCase().onSuccess {
            it.catch { remoteError ->
                _getSchoolListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getSchoolListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getSchoolListResponse.value = error.errorHandling()
        }
    }

    internal fun getUniversityList() = viewModelScope.launch {
        getUniversityListUseCase().onSuccess {
            it.catch { remoteError ->
                _getUniversityListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getUniversityListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getUniversityListResponse.value = error.errorHandling()
        }
    }

    internal fun getCompanyList() = viewModelScope.launch {
        getCompanyListUseCase().onSuccess {
            it.catch { remoteError ->
                _getCompanyListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getCompanyListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getCompanyListResponse.value = error.errorHandling()
        }
    }

    internal fun getGovernmentList() = viewModelScope.launch {
        getGovernmentListUseCase().onSuccess {
            it.catch { remoteError ->
                _getGovernmentListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getGovernmentListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getGovernmentListResponse.value = error.errorHandling()
        }
    }

    private fun getRole() = viewModelScope.launch {
        getAuthorityUseCase()
    }
}