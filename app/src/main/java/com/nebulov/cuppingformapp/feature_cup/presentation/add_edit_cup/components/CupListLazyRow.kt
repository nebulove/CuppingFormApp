package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupEvent
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime

@Composable
fun CupListLazyRow(
    modifier: Modifier = Modifier,
    cupList: List<Cup>,
    saveCup: () -> Unit,
    navControllerEditCup: NavController,
) {



    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        cupList.forEachIndexed() { index, cup ->
            item {
                RoundCupScoreItem(
                    modifier = modifier,
                    onClick = {
                        navControllerEditCup.navigate(
                            Screen.EditCup.route + "?cupId=${cup.id}"
                        )
                        saveCup()
                    },
                    index = cup.id!!
                )
            }
        }
    }
}

@Composable
fun RoundCupScoreItem(
    modifier: Modifier,
    index: Int,
    onClick: () -> Unit
) {


    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .wrapContentWidth()
            .clickable(onClick = onClick),
        color = MaterialTheme.colors.primary,
    ) {
        Box(
            modifier = modifier.width(50.dp), contentAlignment = Alignment.Center,
        )
        {
            Text(
                text = index.toString(),
                fontSize = 13.sp,
                fontWeight = W400,
                modifier = modifier,
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f)
            )
        }

    }

}
