package com.nebulov.cuppingformapp.feautere_cup.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cup")
data class Cup(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val levelOfRoast: Float,

    val fragrance: Float,
    val dry: Float,
    val breakAroma: Float,
    val notesFragrance: String,

    val flavor: Float,
    val notesFlavor: String,

    val aftertaste: Float,
    val notesAftertaste: String,

    val acidity: Float,
    val intensity: Float,
    val notesAcidity: String,

    val body: Float,
    val levelOfBody: Float,
    val notesBody: String,

    val balance: Float,
    val notesBalance: String,

    val uniformity: Int,
    var uCup1: Boolean = false,
    var uCup2: Boolean = false,
    var uCup3: Boolean = false,
    var uCup4: Boolean = false,
    var uCup5: Boolean = false,
    val notesUniformity: String,

    val cleanCup: Int,
    var cCup1: Boolean = false,
    var cCup2: Boolean = false,
    var cCup3: Boolean = false,
    var cCup4: Boolean = false,
    var cCup5: Boolean = false,
    val notesCleanCup: String,

    val sweetness: Int,
    var sCup1: Boolean = false,
    var sCup2: Boolean = false,
    var sCup3: Boolean = false,
    var sCup4: Boolean = false,
    var sCup5: Boolean = false,
    val notesSweetness: String,

    val defects: Int,
    val taintDefects: Int,
    val faultDefects: Int,
    val notesDefects: String,

    val overall: Float,
    val notesOverall: String,

    val finalScore: Float,
    val timestamp: Long,

    var favorite: Boolean = false,
)