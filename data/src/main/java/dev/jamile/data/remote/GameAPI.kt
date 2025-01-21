package dev.jamile.data.remote

import dev.jamile.data.models.GameDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApi {
    @GET("games")
    suspend fun getPopularGames(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10
    ): GameResponse

    @GET("games")
    suspend fun getMostRecentGames(
        @Query("ordering") ordering: String = "-released",
        @Query("page_size") pageSize: Int = 10
    ): GameResponse

    @GET("games/search")
    suspend fun searchGames(@Query("query") query: String): GameResponse

    @GET("games/{gameId}")
    suspend fun getGameDetails(@Path("gameId") gameId: String): GameDetailsDto
}