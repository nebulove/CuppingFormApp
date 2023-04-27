package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.CupItem
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.DefaultFloatingActionButton
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.OrderSection
import com.nebulov.cuppingformapp.feautere_cup.presentation.util.Screen
import kotlinx.coroutines.launch

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


    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.primary,
        floatingActionButton = {
            DefaultFloatingActionButton(
                icon = R.drawable.drop_plus_2_24dp,
                actionOn = { navController.navigate(Screen.AddEditCupScreen.route) },
                contentDescription = "Add cup"
            )
        }
    ) {
        Column(modifier = modifier) {
            OrderSection(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                cupOrder = state.cupOrder,
                onOrderChange = {
                    viewModel.onEvent(CupEvent.Order(it))
                }
            )
            Spacer(modifier = modifier.height(16.dp))
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(items = state.cups) { cup ->
                    CupItem(
                        cup = cup,
                        modifier = modifier
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
                        })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    CupsScreen(navController = navController)
}
