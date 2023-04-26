package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebulov.cuppingform.core.Constants.Companion.EIGHTY_SIX_F
import com.nebulov.cuppingform.core.Constants.Companion.EIGHT_F
import com.nebulov.cuppingform.core.Constants.Companion.EMPTY_STRING
import com.nebulov.cuppingform.core.Constants.Companion.TEN
import com.nebulov.cuppingform.core.Constants.Companion.UNDEFINED_ID
import com.nebulov.cuppingform.core.Constants.Companion.ZERO_F
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.model.InvalidNoteException
import com.nebulov.cuppingformapp.feautere_cup.domain.use_case.CupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCupViewModel @Inject constructor(
    private val cupUseCases: CupUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cupName = mutableStateOf("")
    val cupName: State<String> = _cupName

    private val _fragrance = mutableStateOf(6f)
    val fragrance: State<Float> = _fragrance

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init{
        savedStateHandle.get<Int>("cupId")?.let { cupId ->
            if (cupId != -1){
                viewModelScope.launch {
                    cupUseCases.getCup(cupId)?.also { cup ->
                        currentNoteId = cup.id
                        _cupName.value = cupName.value
                        _fragrance.value = fragrance.value
                    }
                }
            }

        }
    }

    fun onEvent(event: AddEditCupEvent) {
        when (event) {
            is AddEditCupEvent.EnteredName -> {
                _cupName.value = event.value

            }

            is AddEditCupEvent.ChangeFragrance -> {
                _fragrance.value = event.value

            }

            is AddEditCupEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        cupUseCases.addCup(
                            Cup(
                                id = currentNoteId,
                                name = cupName.value,

                                levelOfRoast = ZERO_F,

                                fragrance = fragrance.value,
                                dry = ZERO_F,
                                breakAroma = ZERO_F,
                                notesFragrance = EMPTY_STRING,

                                flavor = EIGHT_F,
                                notesFlavor = EMPTY_STRING,

                                aftertaste = EIGHT_F,
                                notesAftertaste = EMPTY_STRING,

                                acidity = EIGHT_F,
                                intensity = ZERO_F,
                                notesAcidity = EMPTY_STRING,

                                body = EIGHT_F,
                                levelOfBody = ZERO_F,
                                notesBody = EMPTY_STRING,

                                balance = EIGHT_F,
                                notesBalance = EMPTY_STRING,

                                uniformity = TEN,
                                notesUniformity = EMPTY_STRING,

                                cleanCup = TEN,
                                notesCleanCup = EMPTY_STRING,

                                sweetness = TEN,

                                notesSweetness = EMPTY_STRING,

                                defects = TEN,
                                taintDefects = UNDEFINED_ID,
                                faultDefects = UNDEFINED_ID,
                                notesDefects = EMPTY_STRING,

                                overall = EIGHT_F,
                                notesOverall = EMPTY_STRING,

                                finalScore = EIGHTY_SIX_F,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveCup)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save cup"
                            )
                        )
                    }
                }
            }
        }
    }
}

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveCup : UiEvent()
}