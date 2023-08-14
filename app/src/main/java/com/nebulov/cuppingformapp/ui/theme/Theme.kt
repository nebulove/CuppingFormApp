package com.nebulov.cuppingformapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//)

//private val LightCuppingScheme = lightColorScheme(
//    primary = Blue200,
////    primaryVariant = Blue600,
//    secondary = Coral200,
//    background = White,
//    surface = White,
//    onPrimary = White,
//    onSecondary = BlackNight,
//    onBackground = BlackNight,
//    onSurface = BlackNight,
//)
//
//private val DarkCuppingScheme = darkColorScheme(
//    primary = Blue200Dark,
////    primaryVariant = Blue600Dark,
//    secondary = Coral200Dark,
//    background = BlackNight,
//    surface = BlackNight,
//    onPrimary = WhiteNight,
//    onSecondary = WhiteNight,
//    onBackground = WhiteNight,
//    onSurface = WhiteNight,
//)
//

private val LightCuppingPalette = lightColors(
    primary = Blue200,
    primaryVariant = Blue600,
    secondary = Coral200,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = BlackNight,
    onBackground = BlackNight,
    onSurface = BlackNight,
)

private val DarkCuppingPalette = darkColors(
    primary = Blue200Dark,
    primaryVariant = Blue600Dark,
    secondary = Coral200Dark,
    background = BlackNight,
    surface = BlackNight,
    onPrimary = WhiteNight,
    onSecondary = WhiteNight,
    onBackground = WhiteNight,
    onSurface = WhiteNight,
)
private val BWLightCuppingPalette = lightColors(
    primary = White,
    primaryVariant = White,
    secondary = White,
    background = White,
    surface = White,
    onPrimary = Black,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
)
private val BWDarkCuppingPalette = darkColors(
    primary = BlackNight,
    primaryVariant = BlackNight,
    secondary = BlackNight,
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

//
//@Composable
//fun CuppingFormAppTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkCuppingScheme
//        else -> LightCuppingScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}



@Composable
fun CuppingFormTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkCuppingPalette
    } else {
        LightCuppingPalette
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = Typography

    )


    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Blue200Dark
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Blue200
        )
    }
}

@Composable
fun BWCuppingFormTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        BWDarkCuppingPalette
    } else {
        BWLightCuppingPalette
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = Typography

    )


    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = BlackNight
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = White
        )
    }
}


