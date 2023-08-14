package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingform.ui.components.Defects
import com.nebulov.cuppingformapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DefectsForm(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    defectsValue1: State<Int>,
    defectsValue2: State<Int>,
    defectsResult: State<Int>,
    onValueInc1: () -> Unit,
    onValueDec1: () -> Unit,
    onValueInc2: () -> Unit,
    onValueDec2: () -> Unit,
    textDescriptors: State<String>,
    onTextChange: (String) -> Unit,
    coroutineScope: CoroutineScope,
    context: Context,
    textInfo: Int,
    snackbarHostState: SnackbarHostState
) {


    fun onClickInfo(textInfo: Int) {
        coroutineScope.launch {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(textInfo),
                duration = SnackbarDuration.Short,
                actionLabel = context.getString(R.string.hide)
            )
            if (result == SnackbarResult.ActionPerformed) {
                SnackbarResult.Dismissed
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(
                top = 3.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            )
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 11.dp,
                    top = 4.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                stringResource(id = text)
            )
            Spacer(modifier = modifier.size(2.dp))
            InfoIcon(
                modifier = modifier.align(Alignment.CenterVertically),
                onClickInfo = { onClickInfo(textInfo) })
            Spacer(modifier = modifier.weight(1f, true))
            AnimatedValue(defectsResult)
        }
        Column(
            modifier = modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 26.dp,
                    bottom = 4.dp
                )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Defects(
                    text = R.string.Taint,
                    modifier,
                    defectsValue = defectsValue1,
                    onValueDec = { onValueDec1() },
                    onValueInc = { onValueInc1() },
                )
                Defects(
                    text = R.string.Fault, modifier,
                    defectsValue = defectsValue2,
                    onValueDec = { onValueDec2() },
                    onValueInc = { onValueInc2() },
                )
            }
            NotesForm(
                modifier,
                textDescriptors = textDescriptors,
                onValueChange = { onTextChange(it) })
        }
    }
}
