package dev.jamile.data.repository

import dev.jamile.data.generateFakeGame
import dev.jamile.data.models.GameDetailsDto
import dev.jamile.data.models.GameDto
import dev.jamile.data.models.GenreDto
import dev.jamile.data.models.PlatformDetailDto
import dev.jamile.data.models.PlatformDto
import dev.jamile.data.models.TagDto
import dev.jamile.data.remote.GameApi
import dev.jamile.data.remote.GameResponse
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result.Error
import dev.jamile.domain.models.Result.Success
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import java.io.IOException
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertIs

class GameRepositoryModuleImplTest {
    private val gameApi = mockk<GameApi>()
    private val repository = GameRepositoryImpl(gameApi)

// --- getPopularGames ---

    @Test
    fun `getPopularGames returns Success with games`() = runTest {
        // Arrange
        val gameDto = GameDto(
            id = "1", name = "Game 1", genres = emptyList(), platforms = emptyList(),
            imageUrl = "", rating = 4.0, metascore = 80, releaseDate = "2024-01-01",
            description = "Description", tags = emptyList()
        )
        val gameDtos = listOf(gameDto)
        val expectedGames = gameDtos.map { it.toDomainModel() }
        coEvery { gameApi.getPopularGames(page = any()) } returns GameResponse(gameDtos)

        // Act
        val result = repository.getPopularGames(1)

        // Assert
        assertEquals(Success(expectedGames), result)
    }

    @Test
    fun `getPopularGames returns Success with empty list when no games`() = runTest {
        // Arrange
        coEvery { gameApi.getPopularGames(page = any()) } returns GameResponse(emptyList())

        // Act
        val result = repository.getPopularGames(1)

        // Assert
        assertEquals(Success(emptyList<Game>()), result)
    }

    @Test
    fun `getPopularGames returns Error when exception is thrown`() = runTest {
        // Arrange
        val exception = IOException("Network Error")
        coEvery { gameApi.getPopularGames(page = any()) } throws exception

        // Act
        val result = repository.getPopularGames(1)

        // Assert
        assertIs<Error>(result)
        assertEquals(exception, result.exception)
    }

    @Test
    fun `getPopularGames filters excluded tags`() = runTest {
        val gameDto1 = GameDto(
            id = "1", name = "Game 1", genres = emptyList(), platforms = emptyList(),
            imageUrl = "", rating = 4.0, metascore = 80, releaseDate = "2024-01-01",
            description = "Description", tags = listOf(TagDto("nsfw"))
        )
        val gameDto2 = GameDto(
            id = "2", name = "Game 2", genres = emptyList(), platforms = emptyList(),
            imageUrl = "", rating = 4.0, metascore = 80, releaseDate = "2024-01-01",
            description = "Description", tags = emptyList()
        )
        val gameDtos = listOf(gameDto1, gameDto2)
        val expectedGames = listOf(gameDto2).map { it.toDomainModel() }

        coEvery { gameApi.getPopularGames(page = any()) } returns GameResponse(gameDtos)

        val result = repository.getPopularGames(1)

        assertEquals(Success(expectedGames), result)
    }

// --- getRecentGames ---

    @Test
    fun `getRecentGames returns Success with games`() = runTest {
        // Arrange
        val gameDto = GameDto(
            id = "1", name = "Game 1", genres = emptyList(), platforms = emptyList(),
            imageUrl = "", rating = 4.0, metascore = 80, releaseDate = "2024-01-01",
            description = "Description", tags = emptyList()
        )
        val gameDtos = listOf(gameDto)
        val expectedGames = gameDtos.map { it.toDomainModel() }
        coEvery { gameApi.getMostRecentGames(any(), any(), any(), any()) } returns GameResponse(
            gameDtos
        )

        // Act
        val result = repository.getRecentGames(1)

        // Assert
        assertEquals(Success(expectedGames), result)
    }

    @Test
    fun `getRecentGames returns Success with empty list when no games`() = runTest {
        // Arrange
        coEvery { gameApi.getMostRecentGames(any(), any(), any(), any()) } returns GameResponse(
            emptyList()
        )

        // Act
        val result = repository.getRecentGames(1)

        // Assert
        assertEquals(Success(emptyList<Game>()), result)
    }

    @Test
    fun `getRecentGames returns Error when exception is thrown`() = runTest {
        // Arrange
        val exception = IOException("Network Error")
        coEvery { gameApi.getMostRecentGames(any(), any(), any(), any()) } throws exception

        // Act
        val result = repository.getRecentGames(1)

        // Assert
        assertIs<Error>(result)
        assertEquals(exception, result.exception)
    }


// --- searchGamesPaged ---

    @Test
    fun `searchGamesPaged returns Success with games`() = runTest {
        // Arrange
        val gameDtos = listOf(generateFakeGame().toGameDto())
        val expectedGames = gameDtos.map { it.toDomainModel() }
        coEvery { gameApi.searchGames(any(), any(), any()) } returns GameResponse(gameDtos)

        // Act
        val result = repository.searchGamesPaged("query", 1)

        // Assert
        assertEquals(Success(expectedGames), result)
    }

    @Test
    fun `searchGamesPaged returns Success with empty list when no games found`() = runTest {
        // Arrange
        coEvery { gameApi.searchGames(any(), any(), any()) } returns GameResponse(emptyList())

        // Act
        val result = repository.searchGamesPaged("query", 1)

        // Assert
        assertEquals(Success(emptyList<Game>()), result)
    }

    @Test
    fun `searchGamesPaged returns Error when exception is thrown`() = runTest {
        // Arrange
        val exception = IOException("Network Error")
        coEvery { gameApi.searchGames(any(), any(), any()) } throws exception

        // Act
        val result = repository.searchGamesPaged("query", 1)

        // Assert
        assertIs<Error>(result)
        assertEquals(exception, result.exception)
    }

// --- getGameDetails ---

    @Test
    fun `getGameDetails returns Success with game details`() = runTest {
        // Arrange
        val gameDetails = generateFakeGameDetails()
        coEvery { gameApi.getGameDetails(any()) } returns gameDetails.toGameDetailsDto()

        // Act
        val result = repository.getGameDetails("1")

        // Assert
        assertEquals(Success(gameDetails), result)
    }

    @Test
    fun `getGameDetails returns Error when exception is thrown`() = runTest {
        // Arrange
        val exception = IOException("Network Error")
        coEvery { gameApi.getGameDetails(any()) } throws exception

        // Act
        val result = repository.getGameDetails("1")

        // Assert
        assertIs<Error>(result)
        assertEquals(exception, result.exception)
    }
}

fun generateFakeGameDetails(): GameDetails {
    return GameDetails(
        id = Random.nextInt(),
        name = "Fake Game Details",
        description = "A fake game description.",
        metacritic = Random.nextInt(0, 100),
        released = "2024-07-20",
        backgroundImage = "https://example.com/image.jpg",
        rating = Random.nextDouble(0.0, 5.0),
        platforms = listOf("PC", "PS5"),
        genres = listOf("Action", "Adventure")
    )
}

fun Game.toGameDto(): GameDto {
    return GameDto(
        id = id,
        name = name,
        genres = genres?.map { GenreDto(it) } ?: emptyList(),
        platforms = platforms?.map { PlatformDto(PlatformDetailDto(it)) } ?: emptyList(),
        imageUrl = imageUrl,
        rating = rating,
        metascore = metaScore,
        releaseDate = releaseDate,
        description = description,
        tags = tags?.map { TagDto(it) } ?: emptyList()
    )
}

fun GameDetails.toGameDetailsDto(): GameDetailsDto {
    return GameDetailsDto(
        id = id,
        slug = name,
        name = name,
        nameOriginal = name,
        description = description ?: "",
        metacritic = metacritic ?: 0,
        released = released ?: "",
        updated = "",
        backgroundImage = backgroundImage,
        backgroundImageAdditional = null,
        website = null,
        rating = rating,
        ratingTop = 0,
        ratings = null,
        reactions = null,
        added = 0,
        addedByStatus = null,
        playtime = 0,
        screenshotsCount = 0,
        moviesCount = 0,
        creatorsCount = 0,
        achievementsCount = 0,
        parentAchievementsCount = null,
        reviewsTextCount = null,
        ratingsCount = 0,
        alternativeNames = null,
        platforms = platforms?.map { PlatformDto(PlatformDetailDto(it)) } ?: emptyList(),
        genres = genres?.map { GenreDto(it) } ?: emptyList()
    )
}