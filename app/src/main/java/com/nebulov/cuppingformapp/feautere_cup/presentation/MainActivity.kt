package com.nebulov.cuppingformapp.feautere_cup.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.LandingScreen
import com.nebulov.cuppingformapp.ui.theme.CuppingFormTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuppingFormTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colors.background
                ) {

                    MainScreen()
//                    NavScreen()
                }
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colors.primary) {
        var showLandingScreen by rememberSaveable{
            mutableStateOf(true)
        }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false },)
        } else {
            NavScreen()
        }
    }
}
