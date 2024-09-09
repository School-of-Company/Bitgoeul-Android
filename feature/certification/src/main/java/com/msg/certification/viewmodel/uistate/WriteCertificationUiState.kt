package com.msg.certification.viewmodel.uistate

sealed interface WriteCertificationUiState {
    data object Success: WriteCertificationUiState
    data object Loading: WriteCertificationUiState
    data class Error(val exception: Throwable): WriteCertificationUiState
}