package dev.jamile.data.remote

import dev.jamile.data.models.GameDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApi {
    @GET("games")
    suspend fun getPopularGames(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("page") page: Int

    ): GameResponse

    @GET("games")
    suspend fun getMostRecentGames(
        @Query("ordering") ordering: String = "-rating",
        @Query("dates") dates: String,
        @Query("page_size") pageSize: Int = 10,
        @Query("page") page: Int
    ): GameResponse

    @GET("games")
    suspend fun searchGames(
        @Query("search") search: String,
        @Query("page") page: Int,
        @Query("search_precise") searchPrecise: Boolean? = true,
    ): GameResponse

    @GET("games/{gameId}")
    suspend fun getGameDetails(@Path("gameId") gameId: String): GameDetailsDto
}