package com.example.rickymorty.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = RM_Purple,
    onPrimary = RM_OnPrimary,

    background = RM_DarkBackground,
    onBackground = RM_DarkText,

    surface = RM_DarkSurface,
    onSurface = RM_DarkText,

    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = RM_DarkText
)

private val LightColorScheme = lightColorScheme(
    primary = RM_Purple,
    onPrimary = RM_OnPrimary,

    background = RM_Background,
    onBackground = RM_TextPrimary,

    surface = RM_Background,
    onSurface = RM_TextPrimary,

    surfaceVariant = RM_SurfaceVariant,
    onSurfaceVariant = RM_TextPrimary
)

@Composable
fun RickyMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // 🔥 IMPORTANTE: si esto es true, tus colores NO se verán como el mockup en Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}