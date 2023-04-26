package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.AnimatedTextField
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.TopAppBarCuppingForm
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.VerticalSlider
import com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components.DefaultFloatingActionButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditCupScreen(
    navController: NavController,
    viewModel: AddEditCupViewModel = hiltViewModel()
) {
    val nameState = viewModel.cupName.value
    val fragrance = viewModel.fragrance.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var editMessageShown by remember { mutableStateOf(false) }


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
            VerticalSlider(
                text = R.string.Fragrance,
                fragranceCheckSlider = true,
                sliderValue = fragrance,
                onValueChange = { viewModel.onEvent(AddEditCupEvent.ChangeFragrance(it)) },
                onValueChange2 = { },
                onValueChange3 = { },

            )

        }
        AnimatedTextField(
            showOff = { editMessageShown = !editMessageShown },
            sampleName = nameState,
            shown = editMessageShown,
            onTextEdit = {viewModel.onEvent(AddEditCupEvent.EnteredName(it)) }
        )
    }
}
