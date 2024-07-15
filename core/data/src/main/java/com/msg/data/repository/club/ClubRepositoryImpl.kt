package com.msg.data.repository.club

import com.msg.data.mapper.club.toEntity
import com.msg.data.mapper.club.toRequest
import com.msg.model.entity.club.ClubDetailEntity
import com.msg.model.entity.club.ClubListEntity
import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.model.enumdata.HighSchool
import com.msg.model.param.club.PatchClubParam
import com.msg.model.param.club.PostClubParam
import com.msg.network.datasource.club.ClubDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource,
) : ClubRepository {
    override fun getClubList(highSchool: String): Flow<List<ClubListEntity>> {
        return clubDataSource.getClubList(
            highSchool = highSchool
        ).transform { response ->
            response.map { it.toEntity() }
        }
    }

    override fun getClubListForSignUp(highSchool: String): Flow<List<String>> {
        return clubDataSource.getClubListForSignUp(
            highSchool = highSchool
        )
    }

    override fun getClubDetail(id: Long): Flow<ClubDetailEntity> {
        return clubDataSource.getClubDetail(
            id = id
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getStudentBelongClubDetail(
        id: Long,
        studentId: UUID,
    ): Flow<StudentBelongClubEntity> {
        return clubDataSource.getStudentBelongClubDetail(
            id = id,
            studentId = studentId
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getMyClubDetail(): Flow<ClubDetailEntity> {
        return clubDataSource.getMyClubDetail()
            .transform { response ->
                response.toEntity()
            }
    }

    override fun postClub(schoolId: UUID, body: PostClubParam): Flow<Unit> {
        return clubDataSource.postClub(
            schoolId = schoolId,
            body = body.toRequest()
        )
    }

    override fun patchClub(id: Long, body: PatchClubParam): Flow<Unit> {
        return clubDataSource.patchClub(
            id = id,
            body = body.toRequest()
        )
    }

    override fun deleteClub(id: Long): Flow<Unit> {
        return clubDataSource.deleteClub(
            id = id
        )
    }
}