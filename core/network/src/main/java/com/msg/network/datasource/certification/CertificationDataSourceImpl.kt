package com.msg.network.datasource.certification

import com.msg.model.remote.response.certification.DetailCertificationResponse
import com.msg.model.remote.response.lecture.DetailLectureResponse
import com.msg.network.api.CertificationAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class CertificationDataSourceImpl @Inject constructor(
    private val certificationAPI: CertificationAPI,
) : CertificationDataSource {
    override suspend fun getCertificationList(studentId: UUID): Flow<List<DetailCertificationResponse>> =
        flow {
            emit(
                BitgoeulApiHandler<List<DetailCertificationResponse>>()
                    .httpRequest { certificationAPI.getCertificationList(studentId = studentId) }
                    .sendRequest()
            )
        }
}