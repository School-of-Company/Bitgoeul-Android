package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import com.msg.model.param.club.PostClubParam
import java.util.UUID
import javax.inject.Inject

class PostClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    operator fun invoke(schoolId: UUID, body: PostClubParam) = runCatching {
        clubRepository.postClub(schoolId = schoolId, body = body)
    }
}