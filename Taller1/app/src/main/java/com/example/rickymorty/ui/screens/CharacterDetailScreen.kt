package com.example.rickymorty.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickymorty.data.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = {
                    Text("←", modifier = Modifier.padding(16.dp).clickable { onBack() })
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clickable {
                        // ✅ Solo para IA: log del nombre al tocar imagen
                        Log.d("RM_TALLER", "Tap en imagen (detalle) de: ${character.name}")

                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${character.id}")
                        }
                        ctx.startActivity(intent)
                    }
            )

            // ✅ mínimo 6 campos (incluye origin.name y location.name)
            Text("ID: ${character.id}")
            Text("Status: ${character.status}")
            Text("Species: ${character.species}")
            Text("Gender: ${character.gender}")
            Text("Origin: ${character.origin.name}")
            Text("Location: ${character.location.name}")
        }
    }
}