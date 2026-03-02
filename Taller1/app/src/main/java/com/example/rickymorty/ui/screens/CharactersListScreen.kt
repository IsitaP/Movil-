package com.example.rickymorty.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickymorty.data.model.Character
import com.example.rickymorty.ui.components.CharacterItem
import com.example.rickymorty.viewmodel.CharactersViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    vm: CharactersViewModel,
    onOpenDetail: (Character) -> Unit
) {
    val state by vm.state.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Rick & Morty") }) }) { padding ->
        when {
            state.loading -> Box(Modifier.padding(padding).fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.padding(24.dp))
            }
            state.error != null -> Box(Modifier.padding(padding).fillMaxSize()) {
                Text("Error: ${state.error}")
            }
            else -> LazyColumn(contentPadding = padding) {
                stickyHeader {
                    Surface(tonalElevation = 3.dp) {
                        Text(
                            text = "Total personajes: ${state.characters.size}",
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                items(state.characters) { c ->
                    CharacterItem(character = c, onOpenDetail = { onOpenDetail(c) })
                }
            }
        }
    }
}