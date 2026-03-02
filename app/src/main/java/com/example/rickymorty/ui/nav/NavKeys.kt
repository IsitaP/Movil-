package com.example.rickymorty.ui.nav

import androidx.navigation3.runtime.NavKey
import com.example.rickymorty.data.model.Character
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey

@Serializable
object ListScreen : Screen

@Serializable
data class DetailScreen(val character: Character) : Screen