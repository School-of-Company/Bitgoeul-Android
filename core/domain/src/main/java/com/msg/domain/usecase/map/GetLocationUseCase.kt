package com.msg.domain.usecase.map

import com.msg.data.repository.map.MapRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    operator fun invoke(address: String) = runCatching {
        mapRepository.getLocation(address = address)
    }
}