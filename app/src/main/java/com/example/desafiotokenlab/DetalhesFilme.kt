package com.example.desafiotokenlab

import com.squareup.moshi.Json

data class DetalhesFilme(
    val id: Int,
    @field:Json(name = "title") val titulo: String,
    @field:Json(name = "overview") val resumo: String,
    @field:Json(name = "release_date") val dataLancamento: String,
    @field:Json(name = "poster_url") val posterUrl: String,
)