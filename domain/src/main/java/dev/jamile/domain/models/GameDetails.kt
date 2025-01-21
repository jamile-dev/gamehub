package dev.jamile.domain.models

data class GameDetails(
    val id: Int,
    val name: String,
    val description: String?,
    val metacritic: Int?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val platforms: List<String>?
)