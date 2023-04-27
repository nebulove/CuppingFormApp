package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

sealed class AddEditCupEvent {
    data class EnteredName(val value: String) : AddEditCupEvent()

    data class ChangeLevelOfRoast(val value: Float) : AddEditCupEvent()
    data class ChangeFragrance(val value: Float) : AddEditCupEvent()
    data class ChangeDry(val value: Float) : AddEditCupEvent()
    data class ChangeBreakAroma(val value: Float) : AddEditCupEvent()
    data class ChangeFlavor(val value: Float) : AddEditCupEvent()
    data class ChangeAftertaste(val value: Float) : AddEditCupEvent()
    data class ChangeAcidity(val value: Float) : AddEditCupEvent()
    data class ChangeIntensity(val value: Float) : AddEditCupEvent()
    data class ChangeBody(val value: Float) : AddEditCupEvent()
    data class ChangeLevelOfBody(val value: Float) : AddEditCupEvent()
    data class ChangeBalance(val value: Float) : AddEditCupEvent()
    data class ChangeUniformityCup1(val value: Boolean) : AddEditCupEvent()
    data class ChangeUniformityCup2(val value: Boolean) : AddEditCupEvent()
    data class ChangeUniformityCup3(val value: Boolean) : AddEditCupEvent()
    data class ChangeUniformityCup4(val value: Boolean) : AddEditCupEvent()
    data class ChangeUniformityCup5(val value: Boolean) : AddEditCupEvent()

    data class ChangeCleanCup1(val value: Boolean) : AddEditCupEvent()
    data class ChangeCleanCup2(val value: Boolean) : AddEditCupEvent()
    data class ChangeCleanCup3(val value: Boolean) : AddEditCupEvent()
    data class ChangeCleanCup4(val value: Boolean) : AddEditCupEvent()
    data class ChangeCleanCup5(val value: Boolean) : AddEditCupEvent()

    data class ChangeSweetnessCup1(val value: Boolean) : AddEditCupEvent()
    data class ChangeSweetnessCup2(val value: Boolean) : AddEditCupEvent()
    data class ChangeSweetnessCup3(val value: Boolean) : AddEditCupEvent()
    data class ChangeSweetnessCup4(val value: Boolean) : AddEditCupEvent()
    data class ChangeSweetnessCup5(val value: Boolean) : AddEditCupEvent()

    object SaveCup : AddEditCupEvent()
}
