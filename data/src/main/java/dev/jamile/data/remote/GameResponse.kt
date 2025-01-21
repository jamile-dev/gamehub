package dev.jamile.data.remote

import com.google.gson.annotations.SerializedName
import dev.jamile.data.models.GameDto

data class GameResponse(
    @SerializedName("results") val results: List<GameDto>
)