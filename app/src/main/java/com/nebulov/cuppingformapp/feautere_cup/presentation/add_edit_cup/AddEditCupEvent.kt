package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup

sealed class AddEditCupEvent {
    data class EnteredName(val value: String) : AddEditCupEvent()

    data class ChangeFragrance(val value: Float) : AddEditCupEvent()

    object SaveCup : AddEditCupEvent()
}
