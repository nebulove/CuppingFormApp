package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun InfoIcon(
    modifier: Modifier,
    onClickInfo: () -> Unit,
) {
    Icon(
        imageVector = Icons.Outlined.Info,
        contentDescription = stringResource(R.string.info),
        tint = MaterialTheme.colors.primary.copy(alpha = 0.5f),
        modifier = modifier
            .size(16.dp)
            .clickable(onClick = { onClickInfo() })
    )
}

@Composable
fun LockedIcon(
    modifier: Modifier,
    onClickInfo: () -> Unit,
    iconLock: ImageVector
) {
    Icon(
        imageVector = iconLock ,
        contentDescription = stringResource(R.string.lock),
        tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
        modifier = modifier
            .size(26.dp)
            .clickable(onClick = { onClickInfo() })
    )
}