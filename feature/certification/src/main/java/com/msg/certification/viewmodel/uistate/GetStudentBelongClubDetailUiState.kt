package com.msg.certification.viewmodel.uistate

import com.msg.model.entity.club.StudentBelongClubEntity

sealed interface GetStudentBelongClubDetailUiState {
    data class Success(val data: StudentBelongClubEntity): GetStudentBelongClubDetailUiState
    data object Loading: GetStudentBelongClubDetailUiState
    data class Error(val expectation: Throwable): GetStudentBelongClubDetailUiState
}