package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup

@Composable
fun CupItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {

    val iconColor = rememberSaveable { mutableStateOf(true) }

    @Composable
    fun changeIconColor(): Color {
        val color: Color
        if (iconColor.value) {
            color = MaterialTheme.colors.primary
        } else {
            color = MaterialTheme.colors.secondary
        }
        return color
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(
                top = 8.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 3.dp
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                modifier = modifier
                    .size(19.dp)
                    .offset(y = 1.dp)
                    .clickable(onClick = { iconColor.value = !iconColor.value }),
                painter = painterResource(id = R.drawable.outline_water_drop_black_24dp),
                contentDescription = " ",
                tint = changeIconColor()
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                maxLines = 1,
                modifier = modifier
                    .fillMaxWidth(0.7f),
                text = cup.name,
            )
            Spacer(modifier = modifier.weight(1f, true))
            Spacer(modifier = modifier.width(6.dp))
            Text(
                modifier = modifier
                    .fillMaxWidth(0.6f),
                text = cup.finalScore.toString(),
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.width(6.dp))
            Icon(
                modifier = modifier
                    .clickable(onClick = onDeleteClick)
                    .size(19.dp),
                painter = painterResource(
                    R.drawable.delete48
                ),
                contentDescription = stringResource(R.string.Delete),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = modifier.width(6.dp))
        }
    }

}