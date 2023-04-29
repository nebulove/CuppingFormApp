package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.use_case.CupUseCases
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feautere_cup.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CupsViewModel @Inject constructor(
    private val cupUseCases: CupUseCases
) : ViewModel() {


    private val _state = mutableStateOf(CupsState())
    val state: State<CupsState> = _state

    private var recentlyDeletedCup: Cup? = null

    private var getCupJob: Job? = null

    init {
        getCupList(CupOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: CupEvent) {
        when (event) {
            is CupEvent.Order -> {
                if (state.value.cupOrder::class == event.cupOrder::class &&
                    state.value.cupOrder.orderType == event.cupOrder.orderType
                ) {
                    return
                }
                getCupList(event.cupOrder)
            }

            is CupEvent.DeleteCup -> {
                viewModelScope.launch {
                    cupUseCases.deleteCup(event.cup)
                    recentlyDeletedCup = event.cup
                }
            }

            is CupEvent.RestoreCup -> {
                viewModelScope.launch {
                    cupUseCases.addCup(recentlyDeletedCup ?: return@launch)
                    recentlyDeletedCup = null
                }
            }
            is CupEvent.ChangeFavorite ->{
                viewModelScope.launch {
                    val newItem = event.cup.copy(favorite = !event.cup.favorite)
                    cupUseCases.addCup(newItem)
                }
            }
        }
    }

    private fun getCupList(cupOrder: CupOrder) {
        getCupJob?.cancel()
        getCupJob = cupUseCases.getCupList(cupOrder)
            .onEach { cups ->
                _state.value = state.value.copy(
                    cups = cups,
                    cupOrder = cupOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
