package com.example.rickymorty.ui.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickymorty.data.model.Character

@Composable
fun CharacterItem(
    character: Character,
    onOpenDetail: () -> Unit
) {
    val ctx = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpenDetail() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickable {
                    // ✅ Solo para IA: log del nombre al tocar la imagen
                    Log.d("RM_TALLER", "Tap en imagen de: ${character.name}")

                    // ✅ Abrir marcador con id
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${character.id}")
                    }
                    ctx.startActivity(intent)
                }
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text("Status: ${character.status}")
        }

        Icon(Icons.Default.ChevronRight, contentDescription = "Detalle")
    }

    Divider()
}