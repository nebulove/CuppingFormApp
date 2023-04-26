package com.nebulov.cuppingformapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

private val LightCuppingPalette = lightColorScheme(
    primary = Blue200,
//    primaryVariant = Blue600,
    secondary = Coral200,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = BlackNight,
    onBackground = BlackNight,
    onSurface = BlackNight,
)

private val DarkCuppingPalette = darkColorScheme(
    primary = Blue200Dark,
//    primaryVariant = Blue600Dark,
    secondary = Coral200Dark,
    background = BlackNight,
    surface = BlackNight,
    onPrimary = WhiteNight,
    onSecondary = WhiteNight,
    onBackground = WhiteNight,
    onSurface = WhiteNight,
)

//
//val GradientCuppingPalette = lightColors(
//    primary = when (totalScore.value) {
//        100f -> VeryDark
//        in 90f..100f -> Dark
//        in 88f..90f -> Medium2
//        in 85f..88f -> Medium1
//        in 80f..85f -> Medium0
//        in 75f..80f -> Light
//        else -> VeryLight
//    },
/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/


@Composable
fun CuppingFormAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkCuppingPalette
        else -> LightCuppingPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}