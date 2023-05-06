package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

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
import com.nebulov.cuppingformapp.R

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    onShown: () -> Unit,
    onClickBack:()-> Unit
) {


    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        IconButton(
            onClick = { onClickBack() },
        ) {
            Icon(
                modifier = modifier.size(24.dp), painter = painterResource(
                    R.drawable.outline_west_24
                ), contentDescription = stringResource(R.string.ListofCups)
            )
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { onShown() }) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(
                    R.drawable.outline_format_list_bulleted_black_24dp
                ),
                contentDescription = "Станция"
            )
        }
    }
}

