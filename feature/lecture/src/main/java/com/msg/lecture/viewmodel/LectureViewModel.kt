package com.msg.lecture.viewmodel

import androidx.lifecycle.ViewModel
import com.msg.domain.lecture.ApprovePendingLectureUseCase
import com.msg.domain.lecture.GetDetailLectureUseCase
import com.msg.domain.lecture.GetLectureListUseCase
import com.msg.domain.lecture.LectureApplicationUseCase
import com.msg.domain.lecture.OpenLectureUseCase
import com.msg.domain.lecture.RejectPendingLectureUseCase
import javax.inject.Inject

class LectureViewModel @Inject constructor(
    private val approvePendingLectureUseCase: ApprovePendingLectureUseCase,
    private val getDetailLectureUseCase: GetDetailLectureUseCase,
    private val getLectureListUseCase: GetLectureListUseCase,
    private val lectureApplicationUseCase: LectureApplicationUseCase,
    private val openLectureUseCase: OpenLectureUseCase,
    private val rejectPendingLectureUseCase: RejectPendingLectureUseCase
) : ViewModel() {

}