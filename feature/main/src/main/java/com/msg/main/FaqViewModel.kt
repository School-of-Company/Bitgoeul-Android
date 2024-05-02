package com.msg.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.faq.AddFrequentlyAskedQuestionUseCase as AddFAQUseCase
import com.msg.domain.faq.GetFrequentlyAskedQuestionsListUseCase as GetFAQUseCase
import com.msg.main.util.Event
import com.msg.main.util.errorHandling
import com.msg.model.remote.response.faq.GetFrequentlyAskedQuestionDetailResponse as GetFAQDetailResponse
import com.msg.model.remote.request.faq.AddFrequentlyAskedQuestionsRequest as AddFAQRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class FaqViewModel @Inject constructor(
    private val addFAQUseCase: AddFAQUseCase,
    private val getFAQUseCase: GetFAQUseCase,
    authTokenDataSource: AuthTokenDataSource
) : ViewModel() {

    val role = authTokenDataSource.getAuthority()

    private val _addFaqResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val addFaqResponse = _addFaqResponse.asStateFlow()

    private val _getFaqListResponse = MutableStateFlow<Event<List<GetFAQDetailResponse>>>(Event.Loading)
    val getFaqListResponse = _getFaqListResponse.asStateFlow()

    var faqList = mutableStateListOf<GetFAQDetailResponse>()
        private set

    var errorCode: Int = 200
        private set

    fun addFaq(
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

    fun getFaq() = viewModelScope.launch {
        getFAQUseCase().onSuccess {
            it.catch { remoteError ->
                _getFaqListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getFaqListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _addFaqResponse.value = error.errorHandling()
        }
    }
}