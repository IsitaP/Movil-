package com.example.rickymorty.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickymorty.R
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
                    Text(
                        "←",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onBack() }
                    )
                }
            )
        }
    ) { padding ->

        val infiniteTransition = rememberInfiniteTransition(label = "")

        // Animación gradiente superior
        val animatedOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 800f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )

        val topGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF)),
            start = Offset(0f, animatedOffset),
            end = Offset(animatedOffset, 0f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            //  MITAD SUPERIOR (ANIMACIÓN)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(topGradient),
                contentAlignment = Alignment.Center
            ) {

                val borderRotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(4000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = ""
                )

                val borderBrush = Brush.sweepGradient(
                    colors = listOf(Color.Cyan, Color.Magenta, Color.Blue, Color.Cyan)
                )

                Box(contentAlignment = Alignment.Center) {

                    Box(
                        modifier = Modifier
                            .size(220.dp)
                            .graphicsLayer { rotationZ = borderRotation }
                            .border(
                                width = 6.dp,
                                brush = borderBrush,
                                shape = CircleShape
                            )
                    )

                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(200.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${character.id}")
                                }
                                ctx.startActivity(intent)
                            }
                    )
                }
            }

            // MITAD INFERIOR (NORMAL)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(stringResource(R.string.label_id, character.id))
                    Text(stringResource(R.string.label_status, character.status))
                    Text(stringResource(R.string.label_species, character.species))
                    Text(stringResource(R.string.label_gender, character.gender))
                    Text(stringResource(R.string.label_origin, character.origin.name))
                    Text(stringResource(R.string.label_location, character.location.name))
                }
            }
        }
    }
}