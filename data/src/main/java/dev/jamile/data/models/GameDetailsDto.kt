package dev.jamile.data.models

import com.google.gson.annotations.SerializedName
import dev.jamile.domain.models.GameDetails

data class GameDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_original") val nameOriginal: String,
    @SerializedName("description_raw") val description: String,
    @SerializedName("metacritic") val metacritic: Int,
    @SerializedName("released") val released: String,
    @SerializedName("updated") val updated: String,
    @SerializedName("background_image") val backgroundImage: String?,
    @SerializedName("background_image_additional") val backgroundImageAdditional: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("rating_top") val ratingTop: Int,
    @SerializedName("ratings") val ratings: List<Any>?,
    @SerializedName("reactions") val reactions: Map<String, Any>?,
    @SerializedName("added") val added: Int,
    @SerializedName("added_by_status") val addedByStatus: Map<String, Any>?,
    @SerializedName("playtime") val playtime: Int,
    @SerializedName("screenshots_count") val screenshotsCount: Int,
    @SerializedName("movies_count") val moviesCount: Int,
    @SerializedName("creators_count") val creatorsCount: Int,
    @SerializedName("achievements_count") val achievementsCount: Int,
    @SerializedName("parent_achievements_count") val parentAchievementsCount: String?,
    @SerializedName("reviews_text_count") val reviewsTextCount: String?,
    @SerializedName("ratings_count") val ratingsCount: Int,
    @SerializedName("alternative_names") val alternativeNames: List<String>?,
    @SerializedName("platforms") val platforms: List<PlatformDto>?,
    @SerializedName("genres") val genres: List<GenreDto>?,
) {
    fun toDomainModel(): GameDetails =
        GameDetails(
            id = id,
            name = name,
            description = description,
            metacritic = metacritic,
            released = released,
            backgroundImage = backgroundImage,
            rating = rating ?: 0.0,
            platforms = platforms?.map { it.platform.name } ?: emptyList(),
            genres = genres?.map { it.name } ?: emptyList(),
        )
}
