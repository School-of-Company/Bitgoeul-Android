package com.msg.model.entity.government

data class GetGovernmentEntity(
    val governments: List<Government>
) {
    data class Government(
        val id: Long,
        val field: String,
        val governmentName: String
    )
}