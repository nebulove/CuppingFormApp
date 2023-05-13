package com.nebulov.cuppingformapp.feature_cup.presentation.cups

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupEvent
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.AddCupTextField
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.AnimationImage
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.CupItem
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.IconOrderSection
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime
import com.nebulov.cuppingformapp.ui.theme.CuppingFormTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CupsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,

    viewModel: CupsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val scrollState = rememberLazyListState()
    val fabVisible = remember { mutableStateOf(false) }

    val show = rememberSaveable() { mutableStateOf(false) }
    show.value = state.cups.size >= 3

    val addEditCupViewModel: AddEditCupViewModel = hiltViewModel()

    val name = rememberSaveable { mutableStateOf("") }

    val currentOnTimeout = rememberSaveable { mutableStateOf(false) }
    val showWallpaper = remember { mutableStateOf(false) }
    showWallpaper.value = state.cups.isEmpty() && currentOnTimeout.value


    LaunchedEffect(key1 = true) {
        delay(500)
        currentOnTimeout.value = true
    }

    LaunchedEffect(scrollState) {
        var prev = 0
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .collect { index ->
                fabVisible.value = scrollState.firstVisibleItemIndex <= prev && index > 0
                prev = scrollState.firstVisibleItemIndex
            }
    }

    CuppingFormTheme() {

        Scaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.primary,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = fabVisible.value,
                    enter = slideInVertically(initialOffsetY = { it / 2 }),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    FloatingActionButton(
                        onClick = {
                            scope.launch { scrollState.animateScrollToItem(0) }
                        },
                        backgroundColor = MaterialTheme.colors.onPrimary,
                        modifier = modifier.size(38.dp)
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Вернуться к началу списка",
                            tint = MaterialTheme.colors.primary,
                            modifier = modifier
                                .wrapContentSize()
                                .fillMaxSize()
                                .size(36.dp)
                        )
                    }
                }
            }
        ) {
            IconOrderSection(
                onOrderChange = { viewModel.onEvent(CupEvent.Order(it)) },
                cupOrder = state.cupOrder,
                shown = show
            )
            Column(modifier = modifier.animateContentSize(animationSpec = tween(500))) {
                Spacer(modifier = modifier.height(50.dp))
                AddCupTextField(
                    name = name,
                    onValueChange = { name.value = it },
                    addNewCup = {
                        addEditCupViewModel.onEvent(
                            AddEditCupEvent.SaveCupWithName(
                                name.value,
                                state.cups.size
                            )
                        )
                        name.value = ""
                    })
                AnimationImage(shown = showWallpaper)
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it),
                    state = scrollState,
                    contentPadding = PaddingValues(
                        bottom = 72.dp
                    )
                ) {
                    state.cups.forEachIndexed { index, cup ->
                        val showDate =
                            index == 0 || convertLongToTime(cup.timestamp) != convertLongToTime(
                                state.cups[index - 1].timestamp
                            )
                        if (showDate) {
                            item {
                                Text(
                                    text = convertLongToTime(cup.timestamp),
                                    fontSize = 12.sp,
                                    modifier = modifier
                                        .padding(start = 10.dp, top = 6.dp)

                                )
                            }
                        }
                        item {
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
                                onFavoriteChange = { viewModel.onEvent(CupEvent.ChangeFavorite(cup)) },
                                icon = if (cup.favorite) R.drawable.baseline_water_drop_24 else R.drawable.outline_water_drop_black_24dp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



