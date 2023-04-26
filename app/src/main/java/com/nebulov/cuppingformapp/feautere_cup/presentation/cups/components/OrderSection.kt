package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feautere_cup.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    cupOrder: CupOrder = CupOrder.Date(OrderType.Descending),
    onOrderChange: (CupOrder) -> Unit

) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                checked = cupOrder is CupOrder.Title,
                onSelect = { onOrderChange(CupOrder.Title(cupOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                checked = cupOrder is CupOrder.Date,
                onSelect = { onOrderChange(CupOrder.Date(cupOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Value",
                checked = cupOrder is CupOrder.Value,
                onSelect = { onOrderChange(CupOrder.Value(cupOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                checked = cupOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(cupOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                checked = cupOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(cupOrder.copy(OrderType.Descending)) }
            )
        }
    }
}