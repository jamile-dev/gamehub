package dev.jamile.data.models

import com.google.gson.annotations.SerializedName
import dev.jamile.domain.models.Game

data class GameDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("genres") val genres: List<GenreDto>?,
    @SerializedName("platforms") val platforms: List<PlatformDto>?,
    @SerializedName("background_image") val imageUrl: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("metacritic") val metascore: Int?,
    @SerializedName("released") val releaseDate: String?,
    @SerializedName("description_raw") val description: String?,
    @SerializedName("tags") val tags: List<TagDto>?
) {
    fun toDomainModel(): Game {
        return Game(
            id = id ?: "",
            name = name ?: "Unknown",
            genres = genres?.map { it.name } ?: emptyList(),
            platforms = platforms?.map { it.platform.name } ?: emptyList(),
            imageUrl = imageUrl ?: "",
            rating = rating ?: 0.0,
            metaScore = metascore ?: 0,
            releaseDate = releaseDate ?: "Unknown",
            description = description ?: "",
            tags = tags?.map { it.name } ?: emptyList()
        )
    }
}

data class GenreDto(
    @SerializedName("name") val name: String
)

data class PlatformDto(
    @SerializedName("platform") val platform: PlatformDetailDto
)

data class PlatformDetailDto(
    @SerializedName("name") val name: String
)

data class TagDto(
    @SerializedName("name") val name: String
)