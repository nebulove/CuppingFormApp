package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun CompareButton(
    modifier: Modifier = Modifier,
) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.secondary,
            MaterialTheme.colors.primary
        )
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .padding(bottom = 10.dp, top = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            maxLines = 1,
            modifier = modifier
                .padding(
                    top = 3.dp,
                    bottom = 3.dp
                ),
            text = "C O M P A R E",
            fontSize = 28.sp,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.priority24px),
            contentDescription = null,
        )

    }

}