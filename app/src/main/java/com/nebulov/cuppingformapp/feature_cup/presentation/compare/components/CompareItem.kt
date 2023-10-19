package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    scrollState: ScrollState,
    visibilityRL: MutableState<Boolean>,
    visibilityFR: MutableState<Boolean>,
    visibilityDry: MutableState<Boolean>,
    visibilityBreak: MutableState<Boolean>,
    visibilityFlavor: MutableState<Boolean>,
    visibilityAc: MutableState<Boolean>,
    visibilityAf: MutableState<Boolean>,
    visibilityInt: MutableState<Boolean>,
    visibilityBody: MutableState<Boolean>,
    visibilityLvl: MutableState<Boolean>,
    visibilityBalance: MutableState<Boolean>,
    visibilityUn: MutableState<Boolean>,
    visibilityCC: MutableState<Boolean>,
    visibilitySw: MutableState<Boolean>,
    visibilityDefects: MutableState<Boolean>,
    visibilityOverall: MutableState<Boolean>,
    visibilityFS: MutableState<Boolean>,
) {


    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .width(80.dp)
    ) {

        TextForCompareItem(text = cup.name, fontSize = 12.sp)
        HorizonStripe()
        if (visibilityRL.value) {
            TextForCompareItem(text = cup.levelOfRoast.toString())
            HorizonStripe()
        }
        if (visibilityFR.value) {
            TextForCompareItem(text = cup.fragrance.toString())
            HorizonStripe()
        }
        if (visibilityDry.value) {
            TextForCompareItem(text = cup.dry.toString())
            HorizonStripe()
        }
        if (visibilityBreak.value) {
            TextForCompareItem(text = cup.breakAroma.toString())
            HorizonStripe()
        }
        if (visibilityFlavor.value) {
            TextForCompareItem(text = cup.flavor.toString())
            HorizonStripe()
        }
        if (visibilityAf.value) {
            TextForCompareItem(text = cup.aftertaste.toString())
            HorizonStripe()
        }
        if (visibilityAc.value) {
            TextForCompareItem(text = cup.acidity.toString())
            HorizonStripe()
        }
        if (visibilityInt.value) {
            TextForCompareItem(text = cup.intensity.toString())
            HorizonStripe()
        }
        if (visibilityBody.value) {
            TextForCompareItem(text = cup.body.toString())
            HorizonStripe()
        }
        if (visibilityLvl.value) {
            TextForCompareItem(text = cup.levelOfBody.toString())
            HorizonStripe()
        }
        if (visibilityBalance.value) {
            TextForCompareItem(text = cup.balance.toString())
            HorizonStripe()
        }
        if (visibilityUn.value) {
            TextForCompareItem(text = cup.uniformity.toString())
            HorizonStripe()
        }
        if (visibilityCC.value) {
            TextForCompareItem(text = cup.cleanCup.toString())
            HorizonStripe()
        }
        if (visibilitySw.value) {
            TextForCompareItem(text = cup.sweetness.toString())
            HorizonStripe()
        }
        if (visibilityDefects.value) {
            TextForCompareItem(text = cup.defects.toString())
            HorizonStripe()
        }
        if (visibilityOverall.value) {
            TextForCompareItem(text = cup.overall.toString())
            HorizonStripe()
        }
        if (visibilityFS.value) {
            TextForCompareItem(text = cup.finalScore.toString())
        }
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