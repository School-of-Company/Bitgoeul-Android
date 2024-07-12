package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import com.msg.model.param.club.PatchClubParam
import javax.inject.Inject

class PatchClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
){
    operator fun invoke(id: Long, body: PatchClubParam) = runCatching {
        clubRepository.patchClub(id = id, body = body)
    }
}