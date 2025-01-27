package dev.jamile.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games")
data class GameDetails(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String?,
    val metacritic: Int?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val platforms: List<String>?,
    val genres: List<String>?,
) {
    fun toGame(): Game =
        Game(
            id = id.toString(),
            name = name,
            genres = genres,
            platforms = platforms,
            imageUrl = backgroundImage,
            rating = rating ?: 0.0,
            metaScore = metacritic ?: 0,
            releaseDate = released ?: "",
            description = description,
            tags = emptyList(),
        )
}
