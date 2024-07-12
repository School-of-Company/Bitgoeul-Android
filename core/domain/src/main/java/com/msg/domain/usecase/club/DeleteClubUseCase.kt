package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import javax.inject.Inject

class DeleteClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
){
    operator fun invoke(id: Long) = runCatching {
        clubRepository.deleteClub(id = id)
    }
}