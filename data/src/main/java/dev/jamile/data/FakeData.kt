package dev.jamile.data

import dev.jamile.domain.models.Game
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun generateFakeGame(
    id: String = Random.nextInt(1000).toString(),
    name: String = "Fake Game ${Random.nextInt(100)}",
    genres: List<String> = listOf("Action", "Adventure", "RPG"),
    platforms: List<String> = listOf("PC", "PlayStation 5", "Xbox Series X"),
    imageUrl: String = "https://via.placeholder.com/150",
    rating: Double = Random.nextDouble(1.0, 5.0),
    metaScore: Int = Random.nextInt(50, 100),
    releaseDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    description: String? = "This is a fake game description.",
    tags: List<String>? = listOf("Singleplayer", "Multiplayer", "Open World")
): Game {
    return Game(
        id = id,
        name = name,
        genres = genres,
        platforms = platforms,
        imageUrl = imageUrl,
        rating = rating,
        metaScore = metaScore,
        releaseDate = releaseDate,
        description = description,
        tags = tags
    )
}