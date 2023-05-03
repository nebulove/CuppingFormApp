package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feautere_cup.domain.util.OrderType

data class CupsState(
    val cups: List<Cup> = emptyList(),
    val cupOrder: CupOrder = CupOrder.Date(OrderType.Descending),

    val name: String = "",

    val levelOfRoast: Float = 6f,

    val fragrance: Float = 6f,
    val dry: Float = 6f,
    val breakAroma: Float = 6f,
    val notesFragrance: String = "",

    val flavor: Float = 6f,
    val notesFlavor: String = "",

    val aftertaste: Float = 6f,
    val notesAftertaste: String = "",

    val acidity: Float = 6f,
    val intensity: Float = 6f,
    val notesAcidity: String = "",

    val body: Float = 6f,
    val levelOfBody: Float = 6f,
    val notesBody: String = "",

    val balance: Float = 6f,
    val notesBalance: String = "",

    val uniformity: Int = 10,
    var uCup1: Boolean = false,
    var uCup2: Boolean = false,
    var uCup3: Boolean = false,
    var uCup4: Boolean = false,
    var uCup5: Boolean = false,
    val notesUniformity: String = "",

    val cleanCup: Int = 10,
    var cCup1: Boolean = false,
    var cCup2: Boolean = false,
    var cCup3: Boolean = false,
    var cCup4: Boolean = false,
    var cCup5: Boolean = false,
    val notesCleanCup: String = "",

    val sweetness: Int = 10,
    var sCup1: Boolean = false,
    var sCup2: Boolean = false,
    var sCup3: Boolean = false,
    var sCup4: Boolean = false,
    var sCup5: Boolean = false,
    val notesSweetness: String = "",

    val defects: Int = 10,
    val taintDefects: Int = 0,
    val faultDefects: Int = 0,
    val notesDefects: String = "",

    val overall: Float = 6f,
    val notesOverall: String = "",

    val finalScore: Float = 86f,
    val timestamp: Long = 1000L,

    var favorite: Boolean = false,
)
