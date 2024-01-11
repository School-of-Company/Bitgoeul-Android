package com.msg.lecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.lecture.ApprovePendingLectureUseCase
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.domain.lecture.RejectPendingLectureUseCase
import com.msg.lecture.util.Event
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.response.lecture.LectureListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val getLectureListUseCase: GetLectureListUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val openLectureUseCase: OpenLectureUseCase,
    private val approvePendingLectureUseCase: ApprovePendingLectureUseCase,
    private val rejectPendingLectureUseCase: RejectPendingLectureUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {
    val role = Authority.valueOf(authTokenDataSource.getAuthority().toString())

    private val _getLectureListResponse =
        MutableStateFlow<Event<LectureListResponse>>(Event.Loading)
    val getLectureListResponse = _getLectureListResponse.asStateFlow()

    fun getLectureList(page: Int, size: Int, status: ApproveStatus, lectureType: LectureType) =
        viewModelScope.launch {
            when (role) {
                Authority.ROLE_STUDENT -> {
                    getLectureListUseCase(

                    )
                }

                Authority.ROLE_TEACHER -> {

                }

                Authority.ROLE_ADMIN -> {

                }

                else -> {}
            }
        }
}