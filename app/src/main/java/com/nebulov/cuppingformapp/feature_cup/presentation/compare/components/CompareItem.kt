package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.HorizonStripe

@Composable
fun CompareItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {


    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .width(80.dp)
    ) {
        TextForCompareItem(text = cup.name, fontSize = 12.sp)
        HorizonStripe()
        TextForCompareItem(text = cup.levelOfRoast.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.fragrance.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.dry.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.breakAroma.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.flavor.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.aftertaste.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.acidity.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.intensity.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.body.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.levelOfBody.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.balance.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.uniformity.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.cleanCup.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.sweetness.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.defects.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.overall.toString())
        HorizonStripe()
        TextForCompareItem(text = cup.finalScore.toString())
    }


}

@Composable
fun TextForCompareItem(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    visibility: Boolean = true
) {
    if (visibility) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            maxLines = 1,
            modifier = modifier
                .heightIn(22.dp)
                .width(90.dp)
        )
    }
}