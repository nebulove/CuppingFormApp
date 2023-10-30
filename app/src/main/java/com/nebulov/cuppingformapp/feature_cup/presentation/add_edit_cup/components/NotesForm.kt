package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.core.Constants.Companion.EMPTY_STRING

@Composable
fun NotesForm(
    modifier: Modifier = Modifier,
    textDescriptors: State<String>,
    onValueChange: (String) -> Unit,
    lock: State<Boolean>
) {

    val expanded = rememberSaveable { mutableStateOf(false) }


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
        if (!expanded.value)
            IconButton(
                modifier = Modifier.size(20.dp),
                onClick = {
                    expanded.value = !expanded.value
                },
                enabled = lock.value
            ) {
                Icon(
                    tint = if (lock.value) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(
                        alpha = 0.24f
                    ),
                    painter = if (textDescriptors.value == EMPTY_STRING) {
                        painterResource(R.drawable.add_notes48)
                    } else painterResource(R.drawable.edit_note48),
                    modifier = modifier.size(24.dp),
                    contentDescription = if (expanded.value) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        Spacer(Modifier.width(6.dp))
        if (textDescriptors.value == EMPTY_STRING && !expanded.value && lock.value)
            Text(
                text = stringResource(R.string.Notes),
                maxLines = 1,
                color = MaterialTheme.colors.primary,
                fontSize = 14.sp,
                modifier = modifier.clickable(onClick = { expanded.value = !expanded.value })
            )
        if (!expanded.value && textDescriptors.value != EMPTY_STRING)
            Text(
                text = textDescriptors.value,
                modifier = modifier.padding(end = 3.dp)
            )
    }
    if (expanded.value) {
        NotesTextField(
            textDescriptors = textDescriptors,
            onClick = { expanded.value = !expanded.value },
            onValueChange = { onValueChange(it) },
            lock = lock
        )
    }
}

@Composable
fun NotesTextField(
    modifier: Modifier = Modifier,
    textDescriptors: State<String>,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit,
    lock: State<Boolean>
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = textDescriptors.value,
        trailingIcon = {
            IconButton(onClick = { onClick() }, enabled = lock.value) {
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
            .focusRequester(focusRequester)
    )

}