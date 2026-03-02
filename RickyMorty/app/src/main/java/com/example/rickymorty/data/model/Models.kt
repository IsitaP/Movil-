package com.example.rickymorty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: PageInfo,
    val results: List<Character>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Origin,
    val location: Location
)

@Serializable data class Origin(val name: String)
@Serializable data class Location(val name: String)