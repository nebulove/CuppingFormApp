package com.nebulov.cuppingformapp.feature_cup.presentation.cups

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.CupUseCases
import com.nebulov.cuppingformapp.feature_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feature_cup.domain.util.OrderType
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

            is CupEvent.ChangeFavorite -> {
                viewModelScope.launch {
                    val newItem = event.cup.copy(favorite = !event.cup.favorite)
                    cupUseCases.addCup(newItem)
                }
            }

            is CupEvent.DeleteSession -> {
                viewModelScope.launch {
                    cupUseCases.deleteSession(event.timestamp)
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

    fun compareRL(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.levelOfRoast != j.levelOfRoast) {
                    return true
                }
            }
        }
        return false
    }

    fun compareFR(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.fragrance != j.fragrance) {
                    return true
                }
            }
        }
        return false
    }

    fun compareDry(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.dry != j.dry) {
                    return true
                }
            }
        }
        return false
    }

    fun compareBreak(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.breakAroma != j.breakAroma) {
                    return true
                }
            }
        }
        return false
    }

    fun compareFlavor(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.flavor != j.flavor) {
                    return true
                }
            }
        }
        return false
    }

    fun compareAc(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.acidity != j.acidity) {
                    return true
                }
            }
        }
        return false
    }

    fun compareAf(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.aftertaste != j.aftertaste) {
                    return true
                }
            }
        }
        return false
    }

    fun compareInt(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.intensity != j.intensity) {
                    return true
                }
            }
        }
        return false
    }

    fun compareBody(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.body != j.body) {
                    return true
                }
            }
        }
        return false
    }

    fun compareLvl(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.levelOfBody != j.levelOfBody) {
                    return true
                }
            }
        }
        return false
    }

    fun compareBalance(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.balance != j.balance) {
                    return true
                }
            }
        }
        return false
    }

    fun compareUn(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.uniformity != j.uniformity) {
                    return true
                }
            }
        }
        return false
    }

    fun compareCC(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.cleanCup != j.cleanCup) {
                    return true
                }
            }
        }
        return false
    }

    fun compareSw(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.sweetness != j.sweetness) {
                    return true
                }
            }
        }
        return false
    }

    fun compareDefects(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.defects != j.defects) {
                    return true
                }
            }
        }
        return false
    }

    fun compareOverall(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.overall != j.overall) {
                    return true
                }
            }
        }
        return false
    }

    fun compareFS(cupList: List<Cup>): Boolean {
        cupList.forEach { i ->
            cupList.forEach { j ->
                if (i.finalScore != j.finalScore) {
                    return true
                }
            }
        }
        return false
    }
}
