package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.CupEvent
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.CupsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CupsScreen(
    navController: NavController,
    viewModel: CupsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier)
        {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                cupOrder = state.cupOrder,
                onOrderChange = {
                    viewModel.onEvent(CupEvent.Order(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
//            AddCupTextField(name = , onValueChange = , addNewCup = ) {
//
//            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.cups) { cup ->
                    CupItem(cup = cup,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
//                                navController.navigate(
//                                    Screen.AddEditNoteScreen.route +
//                                            "?noteId=${cup.id}"
//                                )
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
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}