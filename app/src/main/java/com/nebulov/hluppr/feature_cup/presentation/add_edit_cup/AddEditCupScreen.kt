@file:OptIn(ExperimentalAnimationApi::class)

package com.nebulov.hluppr.feature_cup.presentation.add_edit_cup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.AnimatedTextField
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.CheckBoxForm
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.DefectsForm
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.Graph
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.MainBottomBar
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.ResultList
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.RoastForm
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.TopAppBarCuppingForm
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components.VerticalSlider
import com.nebulov.hluppr.feature_cup.presentation.cups.components.DefaultFloatingActionButton
import com.nebulov.hluppr.ui.theme.CuppingFormTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditCupScreen(
    navController: NavController,
    viewModel: AddEditCupViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val snackBarHostState = SnackbarHostState()

    val nameState = viewModel.cupName
    val levelOfRoast = viewModel.levelOfRoast
    val fragrance = viewModel.fragrance
    val dry = viewModel.dry
    val breakAroma = viewModel.breakAroma
    val flavor = viewModel.flavor
    val aftertaste = viewModel.aftertaste
    val acidity = viewModel.acidity
    val intensity = viewModel.intensity
    val body = viewModel.body
    val levelOfBody = viewModel.levelOfBody
    val balance = viewModel.balance
    val uniformity = viewModel.uniformity
    val uCup1 = viewModel.uCup1
    val uCup2 = viewModel.uCup2
    val uCup3 = viewModel.uCup3
    val uCup4 = viewModel.uCup4
    val uCup5 = viewModel.uCup5
    val cleanCup = viewModel.cleanCup
    val cCup1 = viewModel.cCup1
    val cCup2 = viewModel.cCup2
    val cCup3 = viewModel.cCup3
    val cCup4 = viewModel.cCup4
    val cCup5 = viewModel.cCup5
    val sweetness = viewModel.sweetness
    val sCup1 = viewModel.sCup1
    val sCup2 = viewModel.sCup2
    val sCup3 = viewModel.sCup3
    val sCup4 = viewModel.sCup4
    val sCup5 = viewModel.sCup5
    val defects = viewModel.defects
    val taintDefects = viewModel.taintDefects
    val faultDefects = viewModel.faultDefects
    val overall = viewModel.overall
    val finalScore = viewModel.finalScore
    val notesFragrance = viewModel.notesFragrance
    val notesFlavor = viewModel.notesFlavor
    val notesAftertaste = viewModel.notesAftertaste
    val notesAcidity = viewModel.notesAcidity
    val notesBody = viewModel.notesBody
    val notesBalance = viewModel.notesBalance
    val notesUniformity = viewModel.notesUniformity
    val notesCleanCup = viewModel.notesCleanCup
    val notesSweetness = viewModel.notesSweetness
    val notesDefects = viewModel.notesDefects
    val notesOverall = viewModel.notesOverall
    val timestamp = viewModel.timestamp


    val shownAddEditScreen = rememberSaveable { mutableStateOf(true) }
    val editMessageShown = remember { mutableStateOf(false) }

    val yStep = 1F
    val points = listOf(
        fragrance.value,
        flavor.value,
        aftertaste.value,
        acidity.value,
        body.value,
        balance.value,
        overall.value
    ).map { (it - 5f) }


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditCupViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditCupViewModel.UiEvent.SaveCup -> {}
            }
        }
    }

    AnimatedVisibility(
        visible = shownAddEditScreen.value,
        exit =
        fadeOut(
            animationSpec = tween(250)
        ),
        enter = fadeIn(
            animationSpec = tween(
                250
            )
        )
    )
    {
        CuppingFormTheme() {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                scaffoldState = scaffoldState,
                topBar = {

                },
                floatingActionButton = {
                    DefaultFloatingActionButton(
                        icon = R.drawable.save48,
                        actionOn = {
                            viewModel.onEvent(AddEditCupEvent.SaveCup)
                            Toast.makeText(context, "${nameState.value} saved", Toast.LENGTH_SHORT)
                                .show()
                        },
                        contentDescription = stringResource(R.string.save),
                        shape = RoundedCornerShape(50)
                    )
                },
                floatingActionButtonPosition = FabPosition.Center,
                isFloatingActionButtonDocked = true,
                backgroundColor = MaterialTheme.colors.primary,
                bottomBar = {
                    MainBottomBar(onClickBack = {
                        viewModel.onEvent(AddEditCupEvent.SaveCup)
                        navController.navigateUp()
                    },
                        onShown = {
                            viewModel.onEvent(AddEditCupEvent.SaveCup)
                            shownAddEditScreen.value = !shownAddEditScreen.value
                        })
                }
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .padding(it),
                ) {
                    Spacer(Modifier.height(178.dp))
                    RoastForm(
                        R.string.Roast,
                        levelOfRoast = levelOfRoast,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeLevelOfRoast(it)) },
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackBarHostState,
                        context = context,
                        textInfo = R.string.RoastInfo,
                    )
                    VerticalSlider(
                        text = R.string.Fragrance,
                        fragranceCheckSlider = true,
                        sliderValue = fragrance,
                        sliderValue2 = dry,
                        sliderValue3 = breakAroma,
                        textDescriptors = notesFragrance,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesFragrance(it)) },
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeFragrance(it)) },
                        onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeDry(it)) },
                        onValueChange3 = { viewModel.onEvent(AddEditCupEvent.ChangeBreakAroma(it)) },
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackBarHostState,
                        context = context,
                        textInfo = R.string.FragranceInfo,
                    )
                    VerticalSlider(
                        text = R.string.Flavor,
                        sliderValue = flavor,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeFlavor(it)) },
                        onValueChange2 = { },
                        onValueChange3 = { },
                        textDescriptors = notesFlavor,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesFlavor(it)) },
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackBarHostState,
                        context = context,
                        textInfo = R.string.FlavorInfo,
                    )
                    VerticalSlider(
                        text = R.string.Aftertaste,
                        sliderValue = aftertaste,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeAftertaste(it)) },
                        onValueChange2 = { },
                        onValueChange3 = { },
                        textDescriptors = notesAftertaste,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesAftertaste(it)) },
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackBarHostState,
                        context = context,
                        textInfo = R.string.AftertasteInfo,
                    )
                    VerticalSlider(
                        text = R.string.Acidity,
                        acidityCheckSlider = true,
                        sliderValue = acidity,
                        sliderValue2 = intensity,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeAcidity(it)) },
                        onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeIntensity(it)) },
                        onValueChange3 = { },
                        textDescriptors = notesAcidity,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesAcidity(it)) },
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackBarHostState,
                        context = context,
                        textInfo = R.string.AcidityInfo,
                    )
                    VerticalSlider(
                        text = R.string.Body,
                        bodyCheckSlider = true,
                        sliderValue = body,
                        sliderValue2 = levelOfBody,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeBody(it)) },
                        onValueChange2 = { viewModel.onEvent(AddEditCupEvent.ChangeLevelOfBody(it)) },
                        onValueChange3 = { },
                        textDescriptors = notesBody,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesBody(it)) },
                        coroutineScope = coroutineScope,
                        context = context,
                        snackbarHostState = snackBarHostState,
                        textInfo = R.string.BodyInfo,
                    )
                    VerticalSlider(
                        text = R.string.Balance,
                        sliderValue = balance,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeBalance(it)) },
                        onValueChange2 = {},
                        onValueChange3 = {},
                        textDescriptors = notesBalance,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesBalance(it)) },
                        coroutineScope = coroutineScope,
                        context = context,
                        snackbarHostState = snackBarHostState,
                        textInfo = R.string.BalanceInfo,
                    )
                    CheckBoxForm(
                        text = R.string.Uniformity,
                        checkboxValue = uniformity,
                        textDescriptors = notesUniformity,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesUniformity(it)) },
                        cup1 = uCup1,
                        cup2 = uCup2,
                        cup3 = uCup3,
                        cup4 = uCup4,
                        cup5 = uCup5,
                        onCheckedChange1 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeUniformityCup1(
                                    it
                                )
                            )
                        },
                        onCheckedChange2 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeUniformityCup2(
                                    it
                                )
                            )
                        },
                        onCheckedChange3 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeUniformityCup3(
                                    it
                                )
                            )
                        },
                        onCheckedChange4 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeUniformityCup4(
                                    it
                                )
                            )
                        },
                        onCheckedChange5 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeUniformityCup5(
                                    it
                                )
                            )
                        },
                        snackbarHostState = snackBarHostState,
                        coroutineScope = coroutineScope,
                        context = context,
                        textInfo = R.string.UniformityInfo,
                    )
                    CheckBoxForm(
                        text = R.string.CleanCup,
                        checkboxValue = cleanCup,
                        textDescriptors = notesCleanCup,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesCleanCup(it)) },
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
                        snackbarHostState = snackBarHostState,
                        coroutineScope = coroutineScope,
                        context = context,
                        textInfo = R.string.CleanCupInfo,
                    )
                    CheckBoxForm(
                        text = R.string.Sweetness,
                        checkboxValue = sweetness,
                        textDescriptors = notesSweetness,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesSweetness(it)) },
                        cup1 = sCup1,
                        cup2 = sCup2,
                        cup3 = sCup3,
                        cup4 = sCup4,
                        cup5 = sCup5,
                        onCheckedChange1 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeSweetnessCup1(
                                    it
                                )
                            )
                        },
                        onCheckedChange2 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeSweetnessCup2(
                                    it
                                )
                            )
                        },
                        onCheckedChange3 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeSweetnessCup3(
                                    it
                                )
                            )
                        },
                        onCheckedChange4 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeSweetnessCup4(
                                    it
                                )
                            )
                        },
                        onCheckedChange5 = {
                            viewModel.onEvent(
                                AddEditCupEvent.ChangeSweetnessCup5(
                                    it
                                )
                            )
                        },
                        snackbarHostState = snackBarHostState,
                        coroutineScope = coroutineScope,
                        context = context,
                        textInfo = R.string.SweetnessInfo,
                    )
                    DefectsForm(
                        text = R.string.Defects,
                        textDescriptors = notesDefects,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesDefect(it)) },
                        defectsResult = defects,
                        defectsValue1 = taintDefects,
                        defectsValue2 = faultDefects,
                        onValueDec1 = { viewModel.onEvent(AddEditCupEvent.ChangeTaintDec(0)) },
                        onValueInc1 = { viewModel.onEvent(AddEditCupEvent.ChangeTaintInc(0)) },
                        onValueDec2 = { viewModel.onEvent(AddEditCupEvent.ChangeFaultDec(0)) },
                        onValueInc2 = { viewModel.onEvent(AddEditCupEvent.ChangeFaultInc(0)) },
                        snackbarHostState = snackBarHostState,
                        coroutineScope = coroutineScope,
                        context = context,
                        textInfo = R.string.DefectsInfo,
                    )
                    VerticalSlider(
                        text = R.string.Overall,
                        sliderValue = overall,
                        onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeOverall(it)) },
                        onValueChange2 = {},
                        onValueChange3 = {},
                        textDescriptors = notesOverall,
                        onTextChange = { viewModel.onEvent(AddEditCupEvent.ChangeNotesOverall(it)) },
                        snackbarHostState = snackBarHostState,
                        coroutineScope = coroutineScope,
                        context = context,
                        textInfo = R.string.OverallInfo,
                    )
                    Spacer(Modifier.height(85.dp))
                }
                Column {
                    TopAppBarCuppingForm(
                        name = nameState,
                        finalScore = finalScore,
                        showOff = {
                            editMessageShown.value = !editMessageShown.value
                        })
                    AnimatedTextField(
                        showOff = { editMessageShown.value = !editMessageShown.value },
                        sampleName = nameState,
                        shown = editMessageShown,
                        onTextEdit = { viewModel.onEvent(AddEditCupEvent.EnteredName(it)) }
                    )
                    Graph(
                            modifier = Modifier
                                .fillMaxWidth()
                    ,
                    xValues = (0..6).map { it + 1 },
                    yValues = (0..4).map { (it + 6) },
                    points = points,
                    paddingSpace = 16.dp,
                    verticalStep = yStep
                    )
                }
            }
        }
    }
    AnimatedVisibility(
        visible = !shownAddEditScreen.value,
        exit =
        shrinkHorizontally(
            animationSpec = tween(250)
        ),
        enter = expandHorizontally(
            animationSpec = tween(
                250
            )
        ),
    )
    {
        ResultList(
            nameState = nameState,
            levelOfRoast = levelOfRoast,
            fragrance = fragrance,
            dry = dry,
            breakAroma = breakAroma,
            flavor = flavor,
            aftertaste = aftertaste,
            acidity = acidity,
            intensity = intensity,
            body = body,
            levelOfBody = levelOfBody,
            balance = balance,
            uniformity = uniformity,
            cleanCup = cleanCup,
            sweetness = sweetness,
            defects = defects,
            taintDefects = taintDefects,
            faultDefects = faultDefects,
            overall = overall,
            finalScore = finalScore,
            notesFragrance = notesFragrance,
            notesFlavor = notesFlavor,
            notesAftertaste = notesAftertaste,
            notesAcidity = notesAcidity,
            notesBody = notesBody,
            notesBalance = notesBalance,
            notesUniformity = notesUniformity,
            notesCleanCup = notesCleanCup,
            notesSweetness = notesSweetness,
            notesDefects = notesDefects,
            notesOverall = notesOverall,
            onClick = { shownAddEditScreen.value = !shownAddEditScreen.value },
            timestamp = timestamp,
        )
    }
}
