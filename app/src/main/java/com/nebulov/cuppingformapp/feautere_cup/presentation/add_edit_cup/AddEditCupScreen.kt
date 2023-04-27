package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingform.ui.components.DefectsForm
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.AnimatedTextField
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.CheckBoxForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.RoastForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.TopAppBarCuppingForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.VerticalSlider
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.DefaultFloatingActionButton
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditCupScreen(
    navController: NavController,
    viewModel: AddEditCupViewModel = hiltViewModel()
) {
    val nameState = viewModel.cupName.value
    val levelOfRoast = viewModel.levelOfRoast.value
    val fragrance = viewModel.fragrance.value
    val dry = viewModel.dry.value
    val breakAroma = viewModel.breakAroma.value
    val flavor = viewModel.flavor.value
    val aftertaste = viewModel.aftertaste.value
    val acidity = viewModel.acidity.value
    val intensity = viewModel.intensity.value
    val body = viewModel.body.value
    val levelOfBody = viewModel.levelOfBody.value
    val balance = viewModel.balance.value
    val uniformity = viewModel.uniformity.value
    val uCup1 = viewModel.uCup1.value
    val uCup2 = viewModel.uCup2.value
    val uCup3 = viewModel.uCup3.value
    val uCup4 = viewModel.uCup4.value
    val uCup5 = viewModel.uCup5.value

    val cleanCup = viewModel.cleanCup.value
    val cCup1 = viewModel.cCup1.value
    val cCup2 = viewModel.cCup2.value
    val cCup3 = viewModel.cCup3.value
    val cCup4 = viewModel.cCup4.value
    val cCup5 = viewModel.cCup5.value

    val sweetness = viewModel.sweetness.value
    val sCup1 = viewModel.sCup1.value
    val sCup2 = viewModel.sCup2.value
    val sCup3 = viewModel.sCup3.value
    val sCup4 = viewModel.sCup4.value
    val sCup5 = viewModel.sCup5.value

    val defects = viewModel.defects.value
    val taintDefects = viewModel.taintDefects.value
    val faultDefects = viewModel.faultDefects.value

    val scaffoldState = rememberScaffoldState()

    var editMessageShown by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditCupViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditCupViewModel.UiEvent.SaveCup -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarCuppingForm(
                name = nameState,
                finalScore = 86f,
                showOff = { editMessageShown = !editMessageShown })
        },
        floatingActionButton = {
            DefaultFloatingActionButton(
                icon = R.drawable.save48,
                actionOn = { viewModel.onEvent(AddEditCupEvent.SaveCup) },
                contentDescription = "Save",
                shape = RoundedCornerShape(
                    bottomEnd = 8.dp,
                    topEnd = 20.dp,
                    topStart = 8.dp,
                    bottomStart = 8.dp
                )
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            RoastForm(
                R.string.Roast,
                levelOfRoast = levelOfRoast,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeLevelOfRoast(it)) }
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.RoastInfo,
            )
            VerticalSlider(
                text = R.string.Fragrance,
                fragranceCheckSlider = true,
                sliderValue = fragrance,
                sliderValue2 = dry,
                sliderValue3 = breakAroma,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeFragrance(it)) },
                onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeDry(it)) },
                onValueChange3 = { viewModel.onEvent(AddEditCupEvent.ChangeBreakAroma(it)) },
            )
            VerticalSlider(
                text = R.string.Flavor,
                sliderValue = flavor,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeFlavor(it)) },
                onValueChange2 = { },
                onValueChange3 = { },
//                textDescriptors = notesFlavor,
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.FlavorInfo,
            )
            VerticalSlider(
                text = R.string.Aftertaste,
                sliderValue = aftertaste,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeAftertaste(it)) },
                onValueChange2 = { },
                onValueChange3 = { },
//                textDescriptors = notesAftertaste,
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.AftertasteInfo,
            )
            VerticalSlider(
                text = R.string.Acidity,
                acidityCheckSlider = true,
                sliderValue = acidity,
                sliderValue2 = intensity,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeAcidity(it)) },
                onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeIntensity(it)) },
                onValueChange3 = { },
//                textDescriptors = notesAcidity,
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.AcidityInfo,
            )
            VerticalSlider(
                text = R.string.Body,
                bodyCheckSlider = true,
                sliderValue = body,
                sliderValue2 = levelOfBody,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeBody(it)) },
                onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeLevelOfBody(it)) },
                onValueChange3 = { },
//                textDescriptors = notesBody,
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.BodyInfo,
            )
            VerticalSlider(
                text = R.string.Balance,
                sliderValue = balance,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeBalance(it)) },
                onValueChange2 = {},
                onValueChange3 = {},
//                textDescriptors = notesBalance,
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.BalanceInfo,
            )
            CheckBoxForm(
                text = R.string.Uniformity,
                checkboxValue = uniformity,
                cup1 = uCup1,
                cup2 = uCup2,
                cup3 = uCup3,
                cup4 = uCup4,
                cup5 = uCup5,
                onCheckedChange1 = { viewModel.onEvent(AddEditCupEvent.ChangeUniformityCup1(it)) },
                onCheckedChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeUniformityCup2(it)) },
                onCheckedChange3 = { viewModel.onEvent(AddEditCupEvent.ChangeUniformityCup3(it)) },
                onCheckedChange4 = { viewModel.onEvent(AddEditCupEvent.ChangeUniformityCup4(it)) },
                onCheckedChange5 = { viewModel.onEvent(AddEditCupEvent.ChangeUniformityCup5(it)) },
            )
            CheckBoxForm(
                text = R.string.CleanCup,
                checkboxValue = cleanCup,
                cup1 = cCup1,
                cup2 = cCup2,
                cup3 = cCup3,
                cup4 = cCup4,
                cup5 = cCup5,
                onCheckedChange1 = { viewModel.onEvent(AddEditCupEvent.ChangeCleanCup1(it)) },
                onCheckedChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeCleanCup2(it)) },
                onCheckedChange3 = { viewModel.onEvent(AddEditCupEvent.ChangeCleanCup3(it)) },
                onCheckedChange4 = { viewModel.onEvent(AddEditCupEvent.ChangeCleanCup4(it)) },
                onCheckedChange5 = { viewModel.onEvent(AddEditCupEvent.ChangeCleanCup5(it)) },
            )
            CheckBoxForm(
                text = R.string.Sweetness,
                checkboxValue = sweetness,
                cup1 = sCup1,
                cup2 = sCup2,
                cup3 = sCup3,
                cup4 = sCup4,
                cup5 = sCup5,
                onCheckedChange1 = { viewModel.onEvent(AddEditCupEvent.ChangeSweetnessCup1(it)) },
                onCheckedChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeSweetnessCup2(it)) },
                onCheckedChange3 = { viewModel.onEvent(AddEditCupEvent.ChangeSweetnessCup3(it)) },
                onCheckedChange4 = { viewModel.onEvent(AddEditCupEvent.ChangeSweetnessCup4(it)) },
                onCheckedChange5 = { viewModel.onEvent(AddEditCupEvent.ChangeSweetnessCup5(it)) },
            )
            DefectsForm(
                text = R.string.Defects,
//                textDescriptors = notesDefects,
                defectsResult = defects,
                defectsValue1 = taintDefects,
                defectsValue2 = faultDefects,
                onValueDec1 = { viewModel.onEvent(AddEditCupEvent.ChangeTaintDec(0)) },
                onValueInc1 = { viewModel.onEvent(AddEditCupEvent.ChangeTaintInc(0)) },
                onValueDec2 = { viewModel.onEvent(AddEditCupEvent.ChangeFaultDec(0)) },
                onValueInc2 = { viewModel.onEvent(AddEditCupEvent.ChangeFaultInc(0)) },
//                scaffoldState = scaffoldState,
//                coroutineScope = coroutineScope,
//                context = context,
//                textInfo = R.string.DefectsInfo,
            )
            Spacer(Modifier.height(85.dp))
        }
        AnimatedTextField(
            showOff = { editMessageShown = !editMessageShown },
            sampleName = nameState,
            shown = editMessageShown,
            onTextEdit = { viewModel.onEvent(AddEditCupEvent.EnteredName(it)) }
        )
    }
}
