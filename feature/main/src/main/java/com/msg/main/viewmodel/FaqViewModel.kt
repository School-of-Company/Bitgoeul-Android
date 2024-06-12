package com.msg.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.GetAuthorityUseCase
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
class FaqViewModel @Inject constructor(
    private val addFAQUseCase: AddFAQUseCase,
    private val getFAQUseCase: GetFAQUseCase,
    private val getAuthorityUseCase: GetAuthorityUseCase,
) : ViewModel() {

    val role = getRole().toString()

    private val _addFaqResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val addFaqResponse = _addFaqResponse.asStateFlow()

    private val _getFaqListResponse = MutableStateFlow<Event<List<GetFAQDetailEntity>>>(Event.Loading)
    val getFaqListResponse = _getFaqListResponse.asStateFlow()

    var faqList = mutableStateListOf<GetFAQDetailEntity>()
        private set

    private var errorCode: Int = 200

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
            _addFaqResponse.value = error.errorHandling()
        }
    }

    private fun getRole() = viewModelScope.launch {
        getAuthorityUseCase()
    }
}