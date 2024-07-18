package com.msg.domain.usecase.club

import com.msg.data.repository.club.ClubRepository
import com.msg.model.enumdata.HighSchool
import javax.inject.Inject

class GetClubListUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(highSchool: String) = runCatching {
        clubRepository.getClubList(highSchool = highSchool)
    }
}