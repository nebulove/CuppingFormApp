package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.hluppr.feature_cup.presentation.cups.NavigationIcon

@Composable
fun CupListIconNavigation(
    selectedItemPosition: MutableState<Int> = rememberSaveable { mutableStateOf(0) },
    changeOrder: () -> Unit
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        elevation =0.dp,
        modifier = Modifier
            .height(25.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
    ) {

        val items = listOf(
            NavigationIcon.Single,
            NavigationIcon.Session,
//            NavigationIcon.Custom,

            )
        items.forEachIndexed() { index, item ->
            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                selected = selectedItemPosition.value == index,
                onClick = {
                    selectedItemPosition.value = index
                    changeOrder()
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconRes), contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = item.titleResId))
                }
            )
        }
    }

}
