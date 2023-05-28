package com.nebulov.hluppr.feature_cup.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.nebulov.hluppr.feature_cup.presentation.cups.LandingScreen
import com.nebulov.hluppr.ui.theme.CuppingFormTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuppingFormTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colors.primary
                ) {

                    MainScreen()
                }
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colors.primary) {
        val showLandingScreen = rememberSaveable{
            mutableStateOf(true)
        }
        if (showLandingScreen.value) {
            LandingScreen(onTimeout = { showLandingScreen.value = false },)
        } else {
            NavScreen()
        }
    }
}
