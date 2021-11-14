package com.example.desafiotokenlab

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val URL_BASE = "https://desafio-mobile.nyc3.digitaloceanspaces.com"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface TmdbApiService {
    @GET("/movies")
    fun getFilmes(): Call<List<Filme>>

    @GET("/movies/{id}")
    fun getDetalhesFilme(@Path("id") id: Int): Call<DetalhesFilme>
}

object TmdbApi {
    val retrofitService: TmdbApiService by lazy {
        retrofit.create(TmdbApiService::class.java)
    }
}