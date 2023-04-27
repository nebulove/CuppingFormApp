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

    private val _cupName = mutableStateOf(EMPTY_STRING)
    val cupName: State<String> = _cupName

    private val _levelOfRoast = mutableStateOf(ZERO_F)
    val levelOfRoast: State<Float> = _levelOfRoast

    private val _fragrance = mutableStateOf(EIGHT_F)
    val fragrance: State<Float> = _fragrance

    private val _dry = mutableStateOf(ZERO_F)
    val dry: State<Float> = _dry

    private val _breakAroma = mutableStateOf(ZERO_F)
    val breakAroma: State<Float> = _breakAroma

    private val _flavor = mutableStateOf(EIGHT_F)
    val flavor: State<Float> = _flavor

    private val _aftertaste = mutableStateOf(EIGHT_F)
    val aftertaste: State<Float> = _aftertaste

    private val _acidity = mutableStateOf(EIGHT_F)
    val acidity: State<Float> = _acidity

    private val _intensity = mutableStateOf(ZERO_F)
    val intensity: State<Float> = _intensity

    private val _body = mutableStateOf(EIGHT_F)
    val body: State<Float> = _body

    private val _levelOfBody = mutableStateOf(ZERO_F)
    val levelOfBody: State<Float> = _levelOfBody

    private val _balance = mutableStateOf(EIGHT_F)
    val balance: State<Float> = _balance

    private val _uniformity = mutableStateOf(TEN)
    val uniformity: State<Int> = _uniformity

    private val _cleanCup = mutableStateOf(TEN)
    val cleanCup: State<Int> = _cleanCup

    private val _sweetness = mutableStateOf(TEN)
    val sweetness: State<Int> = _sweetness


    private val _uCup1 = mutableStateOf(false)
    val uCup1: State<Boolean> = _uCup1

    private val _uCup2 = mutableStateOf(false)
    val uCup2: State<Boolean> = _uCup2

    private val _uCup3 = mutableStateOf(false)
    val uCup3: State<Boolean> = _uCup3

    private val _uCup4 = mutableStateOf(false)
    val uCup4: State<Boolean> = _uCup4

    private val _uCup5 = mutableStateOf(false)
    val uCup5: State<Boolean> = _uCup5

    private val _cCup1 = mutableStateOf(false)
    val cCup1: State<Boolean> = _cCup1

    private val _cCup2 = mutableStateOf(false)
    val cCup2: State<Boolean> = _cCup2

    private val _cCup3 = mutableStateOf(false)
    val cCup3: State<Boolean> = _cCup3

    private val _cCup4 = mutableStateOf(false)
    val cCup4: State<Boolean> = _cCup4

    private val _cCup5 = mutableStateOf(false)
    val cCup5: State<Boolean> = _cCup5

    private val _sCup1 = mutableStateOf(false)
    val sCup1: State<Boolean> = _sCup1

    private val _sCup2 = mutableStateOf(false)
    val sCup2: State<Boolean> = _sCup2

    private val _sCup3 = mutableStateOf(false)
    val sCup3: State<Boolean> = _sCup3

    private val _sCup4 = mutableStateOf(false)
    val sCup4: State<Boolean> = _sCup4

    private val _sCup5 = mutableStateOf(false)
    val sCup5: State<Boolean> = _sCup5

    private val _defects = mutableStateOf(0)
    val defects: State<Int> = _defects

    private val _taintDefects = mutableStateOf(0)
    val taintDefects: State<Int> = _taintDefects

    private val _faultDefects = mutableStateOf(0)
    val faultDefects: State<Int> = _faultDefects

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null


    init {
        savedStateHandle.get<Int>("cupId")?.let { cupId ->
            if (cupId != -1) {
                viewModelScope.launch {
                    cupUseCases.getCup(cupId)?.also { cup ->
                        currentNoteId = cup.id
                        _cupName.value = cup.name

                        _levelOfRoast.value = cup.levelOfRoast

                        _fragrance.value = cup.fragrance
                        _dry.value = cup.dry
                        _breakAroma.value = cup.breakAroma

                        _flavor.value = cup.flavor

                        _aftertaste.value = cup.aftertaste

                        _acidity.value = cup.acidity
                        _intensity.value = cup.intensity

                        _body.value = cup.body
                        _levelOfBody.value = cup.levelOfBody

                        _balance.value = cup.balance

                        _uniformity.value = cup.uniformity
                        _uCup1.value = cup.uCup1
                        _uCup2.value = cup.uCup2
                        _uCup3.value = cup.uCup3
                        _uCup4.value = cup.uCup4
                        _uCup5.value = cup.uCup5

                        _cleanCup.value = cup.cleanCup
                        _cCup1.value = cup.cCup1
                        _cCup2.value = cup.cCup2
                        _cCup3.value = cup.cCup3
                        _cCup4.value = cup.cCup4
                        _cCup5.value = cup.cCup5

                        _sweetness.value = cup.sweetness
                        _sCup1.value = cup.sCup1
                        _sCup2.value = cup.sCup2
                        _sCup3.value = cup.sCup3
                        _sCup4.value = cup.sCup4
                        _sCup5.value = cup.sCup5

                        _taintDefects.value = cup.taintDefects
                        _faultDefects.value = cup.faultDefects
                        _defects.value = cup.defects

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

            is AddEditCupEvent.ChangeLevelOfRoast -> {
                _levelOfRoast.value = event.value

            }

            is AddEditCupEvent.ChangeFragrance -> {
                _fragrance.value = event.value

            }

            is AddEditCupEvent.ChangeDry -> {
                _dry.value = event.value

            }

            is AddEditCupEvent.ChangeBreakAroma -> {
                _breakAroma.value = event.value

            }

            is AddEditCupEvent.ChangeFlavor -> {
                _flavor.value = event.value

            }

            is AddEditCupEvent.ChangeAftertaste -> {
                _aftertaste.value = event.value

            }

            is AddEditCupEvent.ChangeAcidity -> {
                _acidity.value = event.value

            }

            is AddEditCupEvent.ChangeIntensity -> {
                _intensity.value = event.value

            }

            is AddEditCupEvent.ChangeBody -> {
                _body.value = event.value

            }

            is AddEditCupEvent.ChangeLevelOfBody -> {
                _levelOfBody.value = event.value

            }

            is AddEditCupEvent.ChangeBalance -> {
                _balance.value = event.value

            }

            is AddEditCupEvent.ChangeUniformityCup1 -> {
                _uCup1.value = event.value
                if (_uCup1.value) {
                    _uniformity.value -= 2
                } else {
                    _uniformity.value += 2
                }

            }

            is AddEditCupEvent.ChangeUniformityCup2 -> {
                _uCup2.value = event.value
                if (_uCup2.value) {
                    _uniformity.value -= 2
                } else {
                    _uniformity.value += 2
                }
            }

            is AddEditCupEvent.ChangeUniformityCup3 -> {
                _uCup3.value = event.value
                if (_uCup3.value) {
                    _uniformity.value -= 2
                } else {
                    _uniformity.value += 2
                }
            }

            is AddEditCupEvent.ChangeUniformityCup4 -> {
                _uCup4.value = event.value
                if (_uCup4.value) {
                    _uniformity.value -= 2
                } else {
                    _uniformity.value += 2
                }
            }

            is AddEditCupEvent.ChangeUniformityCup5 -> {
                _uCup5.value = event.value
                if (_uCup5.value) {
                    _uniformity.value -= 2
                } else {
                    _uniformity.value += 2
                }
            }

            is AddEditCupEvent.ChangeCleanCup1 -> {
                _cCup1.value = event.value
                if (_cCup1.value) {
                    _cleanCup.value -= 2
                } else {
                    _cleanCup.value += 2
                }
            }

            is AddEditCupEvent.ChangeCleanCup2 -> {
                _cCup2.value = event.value
                if (cCup2.value) {
                    _cleanCup.value -= 2
                } else {
                    _cleanCup.value += 2
                }
            }

            is AddEditCupEvent.ChangeCleanCup3 -> {
                _cCup3.value = event.value
                if (_cCup3.value) {
                    _cleanCup.value -= 2
                } else {
                    _cleanCup.value += 2
                }
            }

            is AddEditCupEvent.ChangeCleanCup4 -> {
                _cCup4.value = event.value
                if (_cCup4.value) {
                    _cleanCup.value -= 2
                } else {
                    _cleanCup.value += 2
                }
            }

            is AddEditCupEvent.ChangeCleanCup5 -> {
                _cCup5.value = event.value
                if (_cCup5.value) {
                    _cleanCup.value -= 2
                } else {
                    _cleanCup.value += 2
                }
            }

            is AddEditCupEvent.ChangeSweetnessCup1 -> {
                _sCup1.value = event.value
                if (_sCup1.value) {
                    _sweetness.value -= 2
                } else {
                    _sweetness.value += 2
                }
            }

            is AddEditCupEvent.ChangeSweetnessCup2 -> {
                _sCup2.value = event.value
                if (_sCup2.value) {
                    _sweetness.value -= 2
                } else {
                    _sweetness.value += 2
                }
            }

            is AddEditCupEvent.ChangeSweetnessCup3 -> {
                _sCup3.value = event.value
                if (_sCup3.value) {
                    _sweetness.value -= 2
                } else {
                    _sweetness.value += 2
                }
            }

            is AddEditCupEvent.ChangeSweetnessCup4 -> {
                _sCup4.value = event.value
                if (_sCup4.value) {
                    _sweetness.value -= 2
                } else {
                    _sweetness.value += 2
                }
            }

            is AddEditCupEvent.ChangeSweetnessCup5 -> {
                _sCup5.value = event.value
                if (_sCup5.value) {
                    _sweetness.value -= 2
                } else {
                    _sweetness.value += 2
                }
            }

            is AddEditCupEvent.ChangeTaintInc -> {
                _taintDefects.value++
                _defects.value -= 2
            }

            is AddEditCupEvent.ChangeTaintDec -> {
                _taintDefects.value--
                _defects.value += 2
            }

            is AddEditCupEvent.ChangeFaultDec -> {
                _faultDefects.value--
                _defects.value += 4
            }

            is AddEditCupEvent.ChangeFaultInc -> {
                _faultDefects.value++
                _defects.value -= 4
            }


            is AddEditCupEvent.SaveCup -> {
                viewModelScope.launch {
                    try {
                        cupUseCases.addCup(
                            Cup(
                                name = cupName.value,

                                levelOfRoast = levelOfRoast.value,

                                fragrance = fragrance.value,
                                dry = dry.value,
                                breakAroma = breakAroma.value,

                                notesFragrance = EMPTY_STRING,
                                flavor = flavor.value,
                                notesFlavor = EMPTY_STRING,
                                aftertaste = aftertaste.value,
                                notesAftertaste = EMPTY_STRING,
                                acidity = acidity.value,
                                intensity = intensity.value,
                                notesAcidity = EMPTY_STRING,
                                body = body.value,
                                levelOfBody = levelOfBody.value,
                                notesBody = EMPTY_STRING,
                                balance = balance.value,
                                notesBalance = EMPTY_STRING,
                                uniformity = uniformity.value,
                                uCup1 = uCup1.value,
                                uCup2 = uCup2.value,
                                uCup3 = uCup3.value,
                                uCup4 = uCup4.value,
                                uCup5 = uCup5.value,
                                notesUniformity = EMPTY_STRING,
                                cleanCup = cleanCup.value,
                                cCup1 = cCup1.value,
                                cCup2 = cCup2.value,
                                cCup3 = cCup3.value,
                                cCup4 = cCup4.value,
                                cCup5 = cCup5.value,
                                notesCleanCup = EMPTY_STRING,
                                sweetness = sweetness.value,
                                sCup1 = sCup1.value,
                                sCup2 = sCup2.value,
                                sCup3 = sCup3.value,
                                sCup4 = sCup4.value,
                                sCup5 = sCup5.value,
                                notesSweetness = EMPTY_STRING,
                                defects = defects.value,
                                taintDefects = taintDefects.value,
                                faultDefects = faultDefects.value,
                                notesDefects = EMPTY_STRING,
                                overall = EIGHT_F,
                                notesOverall = EMPTY_STRING,
                                finalScore = EIGHTY_SIX_F,
                                timestamp = System.currentTimeMillis(),
                                id = currentNoteId,
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

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveCup : UiEvent()
    }
}

