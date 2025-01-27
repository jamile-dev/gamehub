package dev.jamile.domain.models

data class Game(
    val id: String,
    val name: String,
    val genres: List<String>?,
    val platforms: List<String>?,
    val imageUrl: String?,
    val rating: Double,
    val metaScore: Int,
    val releaseDate: String,
    val description: String? = null,
    val tags: List<String>? = null
)