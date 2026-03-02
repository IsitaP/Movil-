package com.example.rickymorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickymorty.data.model.Character
import com.example.rickymorty.data.network.RickMortyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val loading: Boolean = true,
    val characters: List<Character> = emptyList(),
    val error: String? = null
)

class CharactersViewModel(
    private val api: RickMortyApi = RickMortyApi()
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        loadOnce()
    }

    private fun loadOnce() {
        if (_state.value.characters.isNotEmpty()) return

        viewModelScope.launch {
            runCatching {
                _state.value = UiState(loading = true)
                api.getCharacters(page = 2).results
            }.onSuccess { list ->

                Log.d("RM_TALLER", "Cantidad de personajes cargados: ${list.size}")

                _state.value = UiState(loading = false, characters = list)
            }.onFailure { e ->
                _state.value = UiState(loading = false, error = e.message ?: "Error")
            }
        }
    }
}