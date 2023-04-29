package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.AddEditCupEvent
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.AddEditCupViewModel
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.AddCupTextField
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.CupItem
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.IconOrderSection
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.WallpaperEmptyList
import com.nebulov.cuppingformapp.feautere_cup.presentation.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CupsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,

    viewModel: CupsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val show = rememberSaveable() { mutableStateOf(false) }
    show.value = state.cups.size >= 3

    val showWallpaper = rememberSaveable() { mutableStateOf(false) }
    showWallpaper.value = state.cups.isEmpty()

    val addEditCupViewModel: AddEditCupViewModel = hiltViewModel()

    val name = rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.primary,
        floatingActionButton = {
//            DefaultFloatingActionButton(
//                icon = R.drawable.drop_plus,
//                actionOn = { navController.navigate(Screen.AddEditCupScreen.route) },
//                contentDescription = "Add cup"
//            )
        }
    ) {
        Column(modifier = modifier) {
            Spacer(modifier = modifier.height(16.dp))
            IconOrderSection(
                onOrderChange = { viewModel.onEvent(CupEvent.Order(it)) },
                cupOrder = state.cupOrder,
                shown = show.value
            )
            AddCupTextField(
                name = name.value,
                onValueChange = { name.value = it },
                addNewCup = {
                    addEditCupViewModel.onEvent(
                        AddEditCupEvent.SaveCupWithName(
                            name.value,
                            state.cups.size
                        )
                    )
                })
            WallpaperEmptyList(shown = showWallpaper.value)
            Spacer(modifier = modifier.height(4.dp))
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(items = state.cups) { cup ->
                    CupItem(
                        cup = cup,
                        modifier = modifier
                            .animateItemPlacement()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditCupScreen.route +
                                            "?cupId=${cup.id}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(CupEvent.DeleteCup(cup))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Cup deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(CupEvent.RestoreCup)
                                }
                            }
                        },
                        onFavoriteChange = { viewModel.onEvent(CupEvent.ChangeFavorite(cup)) })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

