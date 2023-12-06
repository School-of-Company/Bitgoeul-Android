package com.msg.lecture.viewmodel

import androidx.lifecycle.ViewModel
import com.msg.lecture.util.Event
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.lecture.ApprovePendingLectureUseCase
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.LectureApplicationUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.domain.lecture.RejectPendingLectureUseCase
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.model.remote.response.lecture.LectureListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val approvePendingLectureUseCase: ApprovePendingLectureUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val getLectureListUseCase: GetLectureListUseCase,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val openLectureUseCase: OpenLectureUseCase,
    private val rejectPendingLectureUseCase: RejectPendingLectureUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {
    val role = Authority.valueOf(authTokenDataSource.getAuthority().toString())

    private val _getLectureListResponse = MutableStateFlow<Event<LectureListResponse>>(Event.Loading)
    val getLectureListResponse = _getLectureListResponse.asStateFlow()

    private val _getDetailLectureResponse = MutableStateFlow<Event<DetailLectureResponse>>(Event.Loading)
    val getDetailLectureResponse = _getDetailLectureResponse.asStateFlow()

    private val _openLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val openLectureResponse = _openLectureResponse.asStateFlow()

    private val _approvePendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val approvePendingLectureResponse = _approvePendingLectureResponse.asStateFlow()

    private val _rejectPendingLectureResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val rejectPendingLectureResponse = _rejectPendingLectureResponse

    private val _lectureApplicationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val lectureApplicationResponse = _lectureApplicationResponse.asStateFlow()

}