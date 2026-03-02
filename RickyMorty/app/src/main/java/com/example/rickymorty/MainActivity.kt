package com.example.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.rickymorty.ui.nav.DetailScreen
import com.example.rickymorty.ui.nav.ListScreen
import com.example.rickymorty.ui.nav.Screen
import com.example.rickymorty.ui.screens.CharacterDetailScreen
import com.example.rickymorty.ui.screens.CharactersListScreen
import com.example.rickymorty.ui.theme.RickyMortyTheme
import com.example.rickymorty.viewmodel.CharactersViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickyMortyTheme {

                val vm: CharactersViewModel = viewModel()

                val backStack = remember { mutableStateListOf<Screen>(ListScreen) }

                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                ) { key: Screen ->

                    when (key) {

                        ListScreen -> NavEntry(key) {
                            CharactersListScreen(
                                vm = vm,
                                onOpenDetail = { character ->
                                    backStack.add(DetailScreen(character))
                                }
                            )
                        }

                        is DetailScreen -> NavEntry(key) {
                            CharacterDetailScreen(
                                character = key.character,
                                onBack = {
                                    if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}