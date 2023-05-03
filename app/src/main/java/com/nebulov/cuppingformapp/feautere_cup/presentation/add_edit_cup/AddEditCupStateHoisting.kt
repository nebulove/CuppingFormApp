package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.AddEditCupScreen
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.AddEditCupViewModel

@Composable
fun CupsStateHoistingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditCupViewModel = hiltViewModel()
) {


    AddEditCupScreen(
        navController = navController,
        cup = viewModel.cup.value!!,
        nameOnValueChange = {},
        levelOfRoastOnValueChange = {},
        fragranceOnValueChange = {},
        dryOnValueChange = {},
        breakAromaOnValueChange = {},
        flavorOnValueChange = {},
        aftertasteOnValueChange = {},
        acidityOnValueChange = {},
        intensityOnValueChange = {},
        bodyOnValueChange = {},
        levelOfBodyOnValueChange = {},
        balanceOnValueChange = {},
        overallOnValueChange = {},
        uCup1OnValueChange = {},
        uCup2OnValueChange = {},
        uCup3OnValueChange = {},
        uCup4OnValueChange = {},
        uCup5OnValueChange = {},
        cCup1OnValueChange = {},
        cCup2OnValueChange = {},
        cCup3OnValueChange = {},
        cCup4OnValueChange = {},
        cCup5OnValueChange = {},
        sCup1OnValueChange = {},
        sCup2OnValueChange = {},
        sCup3OnValueChange = {},
        sCup4OnValueChange = {},
        sCup5OnValueChange = {},
        onValueDec1 = {},
        onValueInc1 = {},
        onValueDec2 = {},
        onValueInc2 = {},
        notesFragranceOnTextChange = {},
        notesFlavorOnTextChange = {},
        notesAftertasteOnTextChange = {},
        notesAcidityOnTextChange = {},
        notesBodyOnTextChange = {},
        notesBalanceOnTextChange = {},
        notesUniformityOnTextChange = {},
        notesCleanCupOnTextChange = {},
        notesSweetnessOnTextChange = {},
        notesDefectsOnTextChange = {},
        notesOverallOnTextChange = {},
        saveCup = {}
    )


}