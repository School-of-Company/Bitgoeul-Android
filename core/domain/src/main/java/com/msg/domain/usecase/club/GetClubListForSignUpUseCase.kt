package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import javax.inject.Inject

class GetClubListForSignUpUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    operator fun invoke(highSchool: String) = runCatching {
        clubRepository.getClubListForSignUp(highSchool = highSchool)
    }
}