package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feautere_cup.domain.util.OrderType


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
                text = "DESCEND",
                checked = cupOrder.orderType is OrderType.Descending,
                onClick = { onOrderChange(cupOrder.copy(OrderType.Descending)) }
            )
            DefaultIcon(
                icon = R.drawable.outline_diamond_24,
                onClick = { onOrderChange(CupOrder.Value(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Value,
                text = "SCORE"
            )
            DefaultIcon(
                icon = R.drawable.outline_water_drop_black_24dp,
                onClick = { onOrderChange(CupOrder.Favorite(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Favorite,
                text = "DROP"
            )
            DefaultIcon(
                icon = R.drawable.outline_update_24,
                onClick = { onOrderChange(CupOrder.Date(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Date,
                text = "UPDATE"
            )
            DefaultIcon(
                icon = R.drawable.outline_sort_by_alpha_24,
                onClick = { onOrderChange(CupOrder.Title(cupOrder.orderType)) },
                checked = cupOrder is CupOrder.Title,
                text = "NAME"
            )
            DefaultIcon(
                icon = R.drawable.outline_north_24,
                text = "ASCEND",
                checked = cupOrder.orderType is OrderType.Ascending,
                onClick = { onOrderChange(cupOrder.copy(OrderType.Ascending)) }
            )
        }
    }

}


@Composable
fun DefaultIcon(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean,
    text: String,
) {

    val checkedColor: Color = if (checked) MaterialTheme.colors.onPrimary
    else MaterialTheme.colors.onPrimary.copy(
        alpha = 0.3f
    )


    Box(
        modifier = modifier
            .width(50.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .selectable(selected = checked, onClick = {
                onClick()
            }), contentAlignment = Alignment.Center

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "null",
                tint = checkedColor,
                modifier = modifier
                    .size(22.dp)
            )
            Text(
                modifier = modifier,
                text = text, color = checkedColor,
                fontSize = 9.sp
            )
        }
    }


}