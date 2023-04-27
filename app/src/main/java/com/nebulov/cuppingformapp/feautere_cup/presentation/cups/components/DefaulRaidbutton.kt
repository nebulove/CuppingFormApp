package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    checked: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
//    imageVector: ImageVector
) {
    val iconColor by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked, onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.onPrimary,
                unselectedColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.4f)
            )
        )
//        Icon(
//            imageVector = imageVector,
//            contentDescription = "",
//            tint = changeIconColor(iconColor),
//            modifier = modifier.clickable(onClick = onSelect, role = Role.RadioButton)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.body1)
    }

}