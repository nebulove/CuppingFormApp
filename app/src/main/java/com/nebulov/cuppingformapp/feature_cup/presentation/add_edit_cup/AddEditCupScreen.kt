@file:OptIn(ExperimentalAnimationApi::class)

package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components.CupListLazyRow
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components.EditCup
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components.MainBottomBar
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components.TopAppBarCuppingForm
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsScreen
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.DefaultFloatingActionButton
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditCupScreen(
    navController: NavController,
    viewModel: AddEditCupViewModel = hiltViewModel(),
    cupViewModel: CupsViewModel = hiltViewModel(),
    timestamp: Long,
) {


    val cupList = cupViewModel.state.value.cups.filter {
        convertLongToTime(it.timestamp) == convertLongToTime(timestamp)
    }

    val nameState = viewModel.cupName
    val finalScore = viewModel.finalScore

    val snackBarHostState = SnackbarHostState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val shownAddEditScreen = rememberSaveable { mutableStateOf(true) }
    val editMessageShown = remember { mutableStateOf(false) }

    val navControllerEditCup = rememberNavController()

    Column(modifier = Modifier) {
        if (shownAddEditScreen.value) {
            CupListLazyRow(
                cupList = cupList,
                saveCup = {
                    viewModel.onEvent(
                        AddEditCupEvent.SaveCup
                    )
                },
                navControllerEditCup = navControllerEditCup,
            )
        }
        NavHost(
            navController = navControllerEditCup,
            startDestination = Screen.EditCup.route + "?cupId={cupId}"
        ) {
            composable(route = Screen.CupsScreen.route) {
                Text(text = "Cup")
            }
            composable(route = Screen.EditCup.route + "?cupId={cupId}",
                arguments = listOf(
                    navArgument(
                        name = "cupId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )) {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    scaffoldState = scaffoldState,
                    topBar = {
                        if (shownAddEditScreen.value)
                            TopAppBarCuppingForm(
                                name = nameState,
                                finalScore = finalScore,
                                showOff = {
                                    editMessageShown.value = !editMessageShown.value
                                })
                    },
                    floatingActionButton = {
                        if (shownAddEditScreen.value)
                            DefaultFloatingActionButton(
                                icon = R.drawable.save48,
                                actionOn = {
                                    viewModel.onEvent(AddEditCupEvent.SaveCup)
                                    Toast.makeText(
                                        context,
                                        "${nameState.value} saved",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                },
                                contentDescription = "Save",
                                shape = RoundedCornerShape(50)
                            )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true,
                    backgroundColor = MaterialTheme.colors.primary,
                    bottomBar = {
                        if (shownAddEditScreen.value)
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

                    EditCup(
//                        viewModel = viewModel,
                        showOff = { editMessageShown.value = !editMessageShown.value },
                        onTextEdit = { viewModel.onEvent(AddEditCupEvent.EnteredName(it)) },
                        editMessageShown = editMessageShown,
                        shownAddEditScreen = shownAddEditScreen,
                        shownListResult = { shownAddEditScreen.value = !shownAddEditScreen.value }
                    )
                }
            }
        }
    }
}

