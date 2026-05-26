package io.github.woz07.datapeek.data

/**
 * AppConfiguration.kt
 * @author          woz07
 * @description     Data class whose properties are updated from json and written to json
 */

data class AppConfiguration(
    var opacity: Float? = null,     // Opacity? 0.0 = invisible, 1.0 = visible
    var theme: String? = null,      // Dark mode? Light mode?
)
