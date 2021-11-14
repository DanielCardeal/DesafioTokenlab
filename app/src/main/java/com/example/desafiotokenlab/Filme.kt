package com.example.desafiotokenlab

import com.squareup.moshi.Json

/**
 * Um filme da base de dados TMDB.
 *
 * @property id o identificador do filme na base de dados
 * @property titulo o nome do filme
 */
data class Filme(
    val id: Int,
    @field:Json(name = "title") val titulo: String,
    @field:Json(name = "poster_url") val posterUrl: String,
)
