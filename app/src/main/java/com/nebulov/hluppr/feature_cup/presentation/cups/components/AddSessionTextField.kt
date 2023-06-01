package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun AddSessionTextField(
    modifier: Modifier = Modifier,
    count: State<Int>,
    onValueChange: (Int) -> Unit,
    addNewCup: () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .width(IntrinsicSize.Max)
            .wrapContentSize()
            .padding(
                top = 3.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            )
            .clip(
                RoundedCornerShape(
                    bottomEnd = 8.dp,
                    topEnd = 8.dp,
                    topStart = 8.dp,
                    bottomStart = 8.dp
                )
            ),
    ) {
        IntOnlyTextField(
            value = count,
            onValueChange = { onValueChange(it) },
            addNewCup = { addNewCup() })
    }

}

@Composable
fun IntOnlyTextField(
    value: State<Int>,
    onValueChange: (Int) -> Unit,
    addNewCup: () -> Unit
) {
    val text = rememberSaveable { mutableStateOf(value.value.toString()) }

    DisposableEffect(Unit) {
        text.value = ""
        onDispose { }
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.primary
        ),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            DefaultFloatingActionButton(
                icon = R.drawable.drop_plus,
                actionOn = {
                    addNewCup()
                    text.value = ""
                },
                contentDescription = stringResource(R.string.add_cup)
            )
        },
        placeholder = {
            (Text(
                text = (stringResource(R.string.enter_number_of_samples)),
                color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
            ))
        },
        value = text.value,
        onValueChange = { newValue ->
            if (newValue.isEmpty()) {
                text.value = ""
                onValueChange(0)
            } else {
                try {
                    val intValue = newValue.toInt()
                    text.value = intValue.toString()
                    onValueChange(intValue)
                } catch (e: NumberFormatException) {
                    // Ignore non-integer input
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
