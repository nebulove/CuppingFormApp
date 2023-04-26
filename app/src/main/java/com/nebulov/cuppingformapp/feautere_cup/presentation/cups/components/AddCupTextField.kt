package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun AddCupTextField(
    modifier: Modifier = Modifier,
    name: String,
    onValueChange: (String) -> Unit,
    addNewCup: () -> Unit
) {

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = name,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onPrimary
        ),
        singleLine = true,
        maxLines = 1,
        onValueChange = { onValueChange(it) },
        trailingIcon = {
            DefaultFloatingActionButton(
                icon = R.drawable.drop_plus,
                actionOn = { addNewCup() },
                contentDescription = "Add cup"
            )
        },
        placeholder = {
            (Text(
                text = ("Enter sample name"),
                color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
            ))
        }
    )

}


@Composable
fun DefaultFloatingActionButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    actionOn: () -> Unit,
    contentDescription: String,
    shape: RoundedCornerShape = RoundedCornerShape(
        bottomEnd = 8.dp,
        topEnd = 8.dp,
        topStart = 8.dp,
        bottomStart = 8.dp
    )
) {
    FloatingActionButton(
        modifier = modifier,
        shape = shape,
        content = {
            Icon(
                painterResource(
                    icon
                ), contentDescription = contentDescription, modifier = modifier.size(24.dp)
            )
        },
        onClick = { actionOn() },
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
    )
}