package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup


@Composable
fun CupItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onFavoriteChange: () -> Unit,

) {

    val iconColor = cup.favorite
    val scrollState = rememberScrollState()

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(
                top = 8.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 3.dp
            ) )
    {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DeleteIcon(
                modifier = modifier.clickable(onClick = { onFavoriteChange() }),
                icon = R.drawable.outline_water_drop_black_24dp,
                tint = changeIconColor(iconColor),
                text = R.string.favorite,
                start = 8.dp, end = 8.dp
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                maxLines = 1,
                modifier = modifier
                    .fillMaxWidth(0.7f)
                    .horizontalScroll(scrollState).padding(
                        top = 9.dp,
                        bottom = 10.dp),
                text = cup.name,
            )
            Spacer(modifier = modifier.weight(1f, true))
            Spacer(modifier = modifier.width(1.dp))
            Text(
                modifier = modifier
                    .fillMaxWidth(0.6f).padding(
                        top = 9.dp,
                        bottom = 10.dp),
                text = cup.finalScore.toString(),
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.weight(1f, true))
            DeleteIcon(
                modifier = modifier.clickable(onClick = onDeleteClick),
                R.drawable.delete48,
                tint = MaterialTheme.colors.primary,
                text = R.string.Delete,
                start = 4.dp,
                end = 8.dp
            )
        }
    }
}

@Composable
fun DeleteIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    tint: Color,
    @StringRes text: Int,
    start: Dp,
    end: Dp
) {
    Surface(
        color = MaterialTheme.colors.background, modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Icon(
            modifier = modifier
                .wrapContentSize()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp, start = start, end = end
                )
                .size(20.dp),
            painter = painterResource(icon),
            contentDescription = stringResource(text),
            tint = tint
        )
    }

}

@Composable
fun changeIconColor(state: Boolean): Color {
    val color: Color = if (!state) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }
    return color
}
