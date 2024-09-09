package com.msg.certification.viewmodel.uistate

import com.msg.model.entity.certification.CertificationListEntity

sealed interface GetCertificationListUiState {
    data class Success(val data: List<CertificationListEntity>): GetCertificationListUiState
    data class Error(val expectation: Throwable): GetCertificationListUiState
    data object Loading: GetCertificationListUiState
    data object Empty: GetCertificationListUiState
}