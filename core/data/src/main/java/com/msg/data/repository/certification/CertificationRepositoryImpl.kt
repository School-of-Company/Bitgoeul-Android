package com.msg.data.repository.certification

import com.msg.data.mapper.certification.toEntity
import com.msg.data.mapper.certification.toRequest
import com.msg.model.entity.certification.CertificationListEntity
import com.msg.model.param.certification.WriteCertificationParam
import com.msg.network.datasource.certification.CertificationDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val certificationDataSource: CertificationDataSource,
) : CertificationRepository {
    override fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListEntity>> {
        return certificationDataSource.getCertificationListForTeacher(
            studentId = studentId
        ).transform { response ->
            response.map { it.toEntity() }
        }
    }

    override fun getCertificationListForStudent(): Flow<List<CertificationListEntity>> {
        return certificationDataSource.getCertificationListForStudent()
            .transform { response ->
                response.map { it.toEntity() }
            }
    }

    override fun writeCertification(body: WriteCertificationParam): Flow<Unit> {
        return certificationDataSource.writeCertification(
            body = body.toRequest()
        )
    }

    override fun editCertification(
        id: UUID,
        body: WriteCertificationParam,
    ): Flow<Unit> {
        return certificationDataSource.editCertification(
            id = id,
            body = body.toRequest()
        )
    }
}