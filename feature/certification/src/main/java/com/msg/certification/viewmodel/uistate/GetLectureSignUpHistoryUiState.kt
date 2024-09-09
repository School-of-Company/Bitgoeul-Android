package com.msg.certification.viewmodel.uistate

import com.msg.model.entity.lecture.GetLectureSignUpHistoryEntity

sealed interface GetLectureSignUpHistoryUiState {
    data class Success(val data: GetLectureSignUpHistoryEntity): GetLectureSignUpHistoryUiState
    data object Loading: GetLectureSignUpHistoryUiState
    data object Error: GetLectureSignUpHistoryUiState
}