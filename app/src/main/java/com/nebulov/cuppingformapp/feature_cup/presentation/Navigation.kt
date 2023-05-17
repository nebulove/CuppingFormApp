package com.nebulov.cuppingformapp.feature_cup.presentation

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupScreen
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsScreen
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavScreen(
) {
    val navController = rememberNavController()


    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        NavHost(
            navController = navController,
            startDestination = Screen.CupsScreen.route
        ) {
            composable(route = Screen.CupsScreen.route) {
                CupsScreen(navController = navController)
            }
            composable(route = Screen.AddEditCupScreen.route + "?cupId={cupId}&timestamp={timestamp}",
                arguments = listOf(
                    navArgument(
                        name = "cupId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument(
                        name = "timestamp"
                    ){
                        type = NavType.LongType
                        defaultValue = 1L
                    }
                )) {
                val timestamp = it.arguments?.getLong("timestamp") ?: 1L
                AddEditCupScreen(navController = navController, timestamp = timestamp)
            }
        }

    }

}