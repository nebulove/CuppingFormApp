package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.core.Constants.Companion.EMPTY_STRING

@Composable
fun NotesForm(
    modifier: Modifier = Modifier,
    textDescriptors: String,
    onValueChange: (String) -> Unit
) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(bottom = 4.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 50,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        if (!expanded)
            IconButton(
                modifier = Modifier.size(20.dp),
                onClick = { expanded = !expanded }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    painter = if (textDescriptors == EMPTY_STRING) {
                        painterResource(R.drawable.add_notes48)
                    } else painterResource(R.drawable.edit_note48),
                    modifier = modifier.size(24.dp),
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        Spacer(Modifier.width(6.dp))
        if (textDescriptors == EMPTY_STRING && !expanded)
            Text(
                text = stringResource(R.string.Notes),
                maxLines = 1,
                color = MaterialTheme.colors.primary,
                fontSize = 14.sp,
                modifier = modifier.clickable(onClick = { expanded = !expanded })
            )
        if (!expanded && textDescriptors != EMPTY_STRING)
            Text(
                text = textDescriptors,
                modifier = modifier.padding(end = 3.dp)
            )
    }
    if (expanded) {
        TextField(
            value = textDescriptors,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painterResource(
                            R.drawable.send_48
                        ),
                        modifier = modifier.size(24.dp),
                        contentDescription = stringResource(R.string.show_more),
                        tint = MaterialTheme.colors.primary
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f)
            ),
            onValueChange = { onValueChange(it) },
            modifier = modifier
                .fillMaxWidth()
        )
    }
}