package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun AddCupTextField(
    modifier: Modifier = Modifier,
    name: State<String>,
    onValueChange: (String) -> Unit,
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
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = name.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.primary
            ),
            singleLine = true,
            maxLines = 1,
            onValueChange = { onValueChange(it) },
            trailingIcon = {
                DefaultFloatingActionButton(
                    icon = R.drawable.drop_plus,
                    actionOn = {
                        addNewCup()
                    },
                    contentDescription = stringResource(R.string.add_cup)
                )
            },
            placeholder = {
                (Text(
                    text = (stringResource(R.string.enter_sample_name)),
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
                ))
            }
        )
    }

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
                ),
                contentDescription = contentDescription,
                modifier = modifier
                    .size(24.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        },
        onClick = { actionOn() },
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),

)
}