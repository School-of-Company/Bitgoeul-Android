package com.msg.domain.club

import com.msg.data.repository.club.ClubRepository
import com.msg.model.remote.enumdatatype.HighSchool
import javax.inject.Inject

class GetClubListUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(highSchool: HighSchool) = runCatching {
        clubRepository.getClubList(highSchool = highSchool)
    }
}