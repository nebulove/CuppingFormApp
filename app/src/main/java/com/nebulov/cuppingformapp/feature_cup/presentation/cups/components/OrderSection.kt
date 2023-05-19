package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feature_cup.domain.util.OrderType
import com.nebulov.cuppingformapp.ui.theme.TypographyInter


@Composable
fun IconOrderSection(
    modifier: Modifier = Modifier,
    cupOrder: CupOrder = CupOrder.Date(OrderType.Descending),
    onOrderChange: (CupOrder) -> Unit,
    shown: State<Boolean>,
) {
    AnimatedVisibility(
        visible = shown.value,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 350, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 550, easing = FastOutLinearInEasing)
        )
    )
    {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DefaultIcon(
                icon = R.drawable.outline_south_24,
                text = stringResource(R.string.descend),
                checked = cupOrder.orderType is OrderType.Descending,
                onClick = { onOrderChange(cupOrder.copy(OrderType.Descending)) },
                contentDescription = stringResource(R.string.descend)
            )
            DefaultIcon(
                icon = R.drawable.outline_diamond_24,
                onClick = { onOrderChange(CupOrder.Value(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Value,
                text = stringResource(R.string.score),
                contentDescription = stringResource(R.string.score)
            )
            DefaultIcon(
                icon = R.drawable.outline_water_drop_black_24dp,
                onClick = { onOrderChange(CupOrder.Favorite(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Favorite,
                text = stringResource(R.string.like),
                contentDescription = stringResource(R.string.favorite)
            )
            DefaultIcon(
                icon = R.drawable.outline_history_24,
                onClick = { onOrderChange(CupOrder.Date(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Date,
                text = stringResource(R.string.date),
                contentDescription = stringResource(R.string.date)
            )
            DefaultIcon(
                icon = R.drawable.outline_sort_by_alpha_24,
                onClick = { onOrderChange(CupOrder.Title(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Title,
                text = stringResource(R.string.name),
                contentDescription = stringResource(R.string.name)
            )
            DefaultIcon(
                icon = R.drawable.outline_north_24,
                text = stringResource(R.string.ascend),
                checked = cupOrder.orderType is OrderType.Ascending,
                onClick = { onOrderChange(cupOrder.copy(OrderType.Ascending)) },
                contentDescription = stringResource(R.string.ascend)
            )
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DefaultIcon(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean,
    text: String,
    contentDescription: String
) {

    val checkedColor: Color = if (checked) MaterialTheme.colors.onPrimary
    else MaterialTheme.colors.onPrimary.copy(alpha = 0.5f)

    Box(
        modifier = modifier.animateContentSize(tween(240))
            .width(50.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .selectable(selected = checked, onClick = {
                onClick()
            }), contentAlignment = Alignment.Center

    ) {
        Column(modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = checkedColor,
                modifier = modifier
                    .size(22.dp)
            )
            AnimatedVisibility(
                visible = checked,
                exit =
                shrinkVertically (
                    animationSpec = tween(350)),
                enter = slideInVertically(
                    animationSpec = tween(350),
                    initialOffsetY = { it / 2 }
                ),
            )
            {
                Text(
                    modifier = modifier,
                    text = text, color = checkedColor,
                    style = TypographyInter.body2,
                    fontSize = 9.sp
                )
            }
        }
    }


}