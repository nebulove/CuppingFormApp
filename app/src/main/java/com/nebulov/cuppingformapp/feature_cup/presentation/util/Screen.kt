package com.nebulov.cuppingformapp.feature_cup.presentation.util

sealed class Screen(val route: String) {
    object CupsScreen : Screen("cups_screen")
    object AddEditCupScreen : Screen("add_edit_cup_screen")



}