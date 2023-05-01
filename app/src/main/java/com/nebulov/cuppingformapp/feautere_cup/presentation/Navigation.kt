package com.nebulov.cuppingformapp.feautere_cup.presentation

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.AddEditCupScreen
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.CupsScreen
import com.nebulov.cuppingformapp.feautere_cup.presentation.util.Screen

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
            composable(route = Screen.AddEditCupScreen.route + "?cupId={cupId}",
                arguments = listOf(
                    navArgument(
                        name = "cupId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )) {
                AddEditCupScreen(navController = navController)
            }
        }

    }

}