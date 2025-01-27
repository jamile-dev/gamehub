package dev.jamile.presentation.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val parsedDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
    return parsedDate.format(formatter)
}