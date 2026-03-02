package com.example.rickymorty.data.network

import com.example.rickymorty.data.model.CharactersResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class RickMortyApi {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun getCharacters(page: Int): CharactersResponse {
        return client.get("https://rickandmortyapi.com/api/character") {
            parameter("page", page)
        }.body()
    }
}