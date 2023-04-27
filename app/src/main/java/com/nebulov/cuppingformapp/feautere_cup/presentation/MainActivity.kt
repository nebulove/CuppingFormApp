package com.nebulov.cuppingformapp.feautere_cup.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
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
                    NavScreen()
                }
            }
        }
    }
}
