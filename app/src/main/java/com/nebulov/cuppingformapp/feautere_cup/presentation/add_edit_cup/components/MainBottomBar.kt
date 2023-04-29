package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.core.Constants
import com.nebulov.cuppingformapp.R

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {


    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                navController.navigate(Constants.CUP_SCREEN)
            },
        ) {
            Icon(
                modifier = modifier.size(24.dp), painter = painterResource(
                    R.drawable.outline_format_list_bulleted_black_24dp
                ), contentDescription = stringResource(R.string.ListofCups)
            )
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick =
        {

        }) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(
                    R.drawable.outline_local_cafe_black_24dp
                ),
                contentDescription = "Станция"
            )
        }
    }
}

