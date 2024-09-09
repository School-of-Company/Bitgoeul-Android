package com.msg.certification.viewmodel.uistate

sealed interface EditCertificationUiState {
    data object Success: EditCertificationUiState
    data class Error(val exception: Throwable): EditCertificationUiState
}