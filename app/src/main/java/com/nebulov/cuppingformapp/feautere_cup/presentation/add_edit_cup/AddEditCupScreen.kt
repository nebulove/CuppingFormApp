package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebulov.cuppingform.ui.components.DefectsForm
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.AnimatedTextField
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.CheckBoxForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.MainBottomBar
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.RoastForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.TopAppBarCuppingForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.VerticalSlider
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.DefaultFloatingActionButton
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditCupScreen(
    navController: NavController,
    cup: Cup,
    nameOnValueChange: (String) -> Unit,
    levelOfRoastOnValueChange: (Float) -> Unit,
    fragranceOnValueChange: (Float) -> Unit,
    dryOnValueChange: (Float) -> Unit,
    breakAromaOnValueChange: (Float) -> Unit,
    flavorOnValueChange: (Float) -> Unit,
    aftertasteOnValueChange: (Float) -> Unit,
    acidityOnValueChange: (Float) -> Unit,
    intensityOnValueChange: (Float) -> Unit,
    bodyOnValueChange: (Float) -> Unit,
    levelOfBodyOnValueChange: (Float) -> Unit,
    balanceOnValueChange: (Float) -> Unit,
    overallOnValueChange: (Float) -> Unit,

    uCup1OnValueChange: (Boolean) -> Unit,
    uCup2OnValueChange: (Boolean) -> Unit,
    uCup3OnValueChange: (Boolean) -> Unit,
    uCup4OnValueChange: (Boolean) -> Unit,
    uCup5OnValueChange: (Boolean) -> Unit,

    cCup1OnValueChange: (Boolean) -> Unit,
    cCup2OnValueChange: (Boolean) -> Unit,
    cCup3OnValueChange: (Boolean) -> Unit,
    cCup4OnValueChange: (Boolean) -> Unit,
    cCup5OnValueChange: (Boolean) -> Unit,

    sCup1OnValueChange: (Boolean) -> Unit,
    sCup2OnValueChange: (Boolean) -> Unit,
    sCup3OnValueChange: (Boolean) -> Unit,
    sCup4OnValueChange: (Boolean) -> Unit,
    sCup5OnValueChange: (Boolean) -> Unit,

    onValueDec1: () -> Unit,
    onValueInc1: () -> Unit,
    onValueDec2: () -> Unit,
    onValueInc2: () -> Unit,

    notesFragranceOnTextChange: (String) -> Unit,
    notesFlavorOnTextChange: (String) -> Unit,
    notesAftertasteOnTextChange: (String) -> Unit,
    notesAcidityOnTextChange: (String) -> Unit,
    notesBodyOnTextChange: (String) -> Unit,
    notesBalanceOnTextChange: (String) -> Unit,
    notesUniformityOnTextChange: (String) -> Unit,
    notesCleanCupOnTextChange: (String) -> Unit,
    notesSweetnessOnTextChange: (String) -> Unit,
    notesDefectsOnTextChange: (String) -> Unit,
    notesOverallOnTextChange: (String) -> Unit,

    saveCup: () -> Unit


) {
    val context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var editMessageShown by remember { mutableStateOf(false) }

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is AddEditCupViewModel.UiEvent.ShowSnackBar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
//                }
//
//                is AddEditCupViewModel.UiEvent.SaveCup -> {}
//            }
//        }
//    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarCuppingForm(
                name = cup.name,
                finalScore = cup.finalScore,
                showOff = { editMessageShown = !editMessageShown })
        },
        floatingActionButton = {
            DefaultFloatingActionButton(
                icon = R.drawable.save48,
                actionOn = {
                    saveCup()
                    Toast.makeText(context, "${cup.name} saved", Toast.LENGTH_SHORT).show()
                },
                contentDescription = "Save",
                shape = RoundedCornerShape(50)
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = MaterialTheme.colors.primary,
        bottomBar = {
            MainBottomBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            RoastForm(
                R.string.Roast,
                levelOfRoast = cup.levelOfRoast,
                onValueChange = { levelOfRoastOnValueChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.RoastInfo,
            )
            VerticalSlider(
                text = R.string.Fragrance,
                fragranceCheckSlider = true,
                sliderValue = cup.fragrance,
                sliderValue2 = cup.dry,
                sliderValue3 = cup.breakAroma,
                textDescriptors = cup.notesFragrance,
                onTextChange = { notesFragranceOnTextChange(it) },
                onValueChange = { fragranceOnValueChange(it) },
                onValueChange2 = { dryOnValueChange(it) },
                onValueChange3 = { breakAromaOnValueChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.FragranceInfo,
            )
            VerticalSlider(
                text = R.string.Flavor,
                sliderValue = cup.flavor,
                onValueChange = { flavorOnValueChange(it) },
                onValueChange2 = { },
                onValueChange3 = { },
                textDescriptors = cup.notesFlavor,
                onTextChange = { notesFlavorOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.FlavorInfo,
            )
            VerticalSlider(
                text = R.string.Aftertaste,
                sliderValue = cup.aftertaste,
                onValueChange = { aftertasteOnValueChange(it) },
                onValueChange2 = { },
                onValueChange3 = { },
                textDescriptors = cup.notesAftertaste,
                onTextChange = { notesAftertasteOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.AftertasteInfo,
            )
            VerticalSlider(
                text = R.string.Acidity,
                acidityCheckSlider = true,
                sliderValue = cup.acidity,
                sliderValue2 = cup.intensity,
                onValueChange = { acidityOnValueChange(it) },
                onValueChange2 = { intensityOnValueChange(it) },
                onValueChange3 = { },
                textDescriptors = cup.notesAcidity,
                onTextChange = { notesAcidityOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.AcidityInfo,
            )
            VerticalSlider(
                text = R.string.Body,
                bodyCheckSlider = true,
                sliderValue = cup.body,
                sliderValue2 = cup.levelOfBody,
                onValueChange = { bodyOnValueChange(it) },
                onValueChange2 = { levelOfBodyOnValueChange(it) },
                onValueChange3 = { },
                textDescriptors = cup.notesBody,
                onTextChange = { notesBodyOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.BodyInfo,
            )
            VerticalSlider(
                text = R.string.Balance,
                sliderValue = cup.balance,
                onValueChange = { balanceOnValueChange(it) },
                onValueChange2 = {},
                onValueChange3 = {},
                textDescriptors = cup.notesBalance,
                onTextChange = { notesBalanceOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.BalanceInfo,
            )
            CheckBoxForm(
                text = R.string.Uniformity,
                checkboxValue = cup.uniformity,
                textDescriptors = cup.notesUniformity,
                onTextChange = { notesUniformityOnTextChange(it) },
                cup1 = cup.uCup1,
                cup2 = cup.uCup2,
                cup3 = cup.uCup3,
                cup4 = cup.uCup4,
                cup5 = cup.uCup5,
                onCheckedChange1 = { uCup1OnValueChange(it) },
                onCheckedChange2 = { uCup2OnValueChange(it) },
                onCheckedChange3 = { uCup3OnValueChange(it) },
                onCheckedChange4 = { uCup4OnValueChange(it) },
                onCheckedChange5 = { uCup5OnValueChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.UniformityInfo,
            )
            CheckBoxForm(
                text = R.string.CleanCup,
                checkboxValue = cup.cleanCup,
                textDescriptors = cup.notesCleanCup,
                onTextChange = { notesCleanCupOnTextChange(it) },
                cup1 = cup.cCup1,
                cup2 = cup.cCup2,
                cup3 = cup.cCup3,
                cup4 = cup.cCup4,
                cup5 = cup.cCup5,
                onCheckedChange1 = { cCup1OnValueChange(it) },
                onCheckedChange2 = { cCup2OnValueChange(it) },
                onCheckedChange3 = { cCup3OnValueChange(it) },
                onCheckedChange4 = { cCup4OnValueChange(it) },
                onCheckedChange5 = { cCup5OnValueChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.CleanCupInfo,
            )
            CheckBoxForm(
                text = R.string.Sweetness,
                checkboxValue = cup.sweetness,
                textDescriptors = cup.notesSweetness,
                onTextChange = { notesSweetnessOnTextChange(it) },
                cup1 = cup.sCup1,
                cup2 = cup.sCup2,
                cup3 = cup.sCup3,
                cup4 = cup.sCup4,
                cup5 = cup.sCup5,
                onCheckedChange1 = { sCup1OnValueChange(it) },
                onCheckedChange2 = { sCup2OnValueChange(it) },
                onCheckedChange3 = { sCup3OnValueChange(it) },
                onCheckedChange4 = { sCup4OnValueChange(it) },
                onCheckedChange5 = { sCup5OnValueChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.SweetnessInfo,
            )
            DefectsForm(
                text = R.string.Defects,
                textDescriptors = cup.notesDefects,
                onTextChange = { notesDefectsOnTextChange(it) },
                defectsResult = cup.defects,
                defectsValue1 = cup.taintDefects,
                defectsValue2 = cup.faultDefects,
                onValueDec1 = { onValueDec1() },
                onValueInc1 = { onValueInc1() },
                onValueDec2 = { onValueDec2() },
                onValueInc2 = { onValueInc2() },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.DefectsInfo,
            )
            VerticalSlider(
                text = R.string.Overall,
                sliderValue = cup.overall,
                onValueChange = { overallOnValueChange(it) },
                onValueChange2 = {},
                onValueChange3 = {},
                textDescriptors = cup.notesOverall,
                onTextChange = { notesOverallOnTextChange(it) },
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                context = context,
                textInfo = R.string.OverallInfo,
            )
            Spacer(Modifier.height(85.dp))
        }
        AnimatedTextField(
            showOff = { editMessageShown = !editMessageShown },
            sampleName = cup.name,
            shown = editMessageShown,
            onTextEdit = { nameOnValueChange(it) }
        )
    }
}
