package com.msg.data.repository.club

import com.msg.model.entity.club.ClubDetailEntity
import com.msg.model.entity.club.ClubListEntity
import com.msg.model.entity.club.StudentBelongClubEntity
import com.msg.model.enumdata.HighSchool
import com.msg.model.param.club.PatchClubParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubRepository {
    fun getClubList(highSchool: HighSchool): Flow<List<ClubListEntity>>
    fun getClubDetail(id: Long): Flow<ClubDetailEntity>
    fun getStudentBelongClubDetail(id: Long, studentId: UUID): Flow<StudentBelongClubEntity>
    fun getMyClubDetail(): Flow<ClubDetailEntity>
    fun patchClub(id: Long, body: PatchClubParam): Flow<Unit>
}