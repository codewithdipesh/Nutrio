package com.codewithdipesh.mylibrary.remote.dto

import com.squareup.moshi.Json

data class Nutrients(
    val CA: CA?,
    @Json(name = "CHOCDF")
    val carbs: CHOCDF?,
    val CHOLE: CHOLE?,
    @Json(name = "ENERC_KCAL")
    val calories: ENERCKCAL?,
    val FAMS: FAMS?,
    val FAPU: FAPU?,
    val FASAT: FASAT?,
    @Json(name = "FAT")
    val fat: FAT?,
    val FE: FE?,
    @Json(name = "FIBTG")
    val fibers: FIBTG?,
    val FOLAC: FOLAC?,
    val FOLDFE: FOLDFE?,
    val FOLFD: FOLFD?,
    val K: K?,
    val MG: MG?,
    val NA: NA?,
    val NIA: NIA?,
    val P: P?,
    @Json(name = "PROCNT")
    val protein: PROCNT?,
    val RIBF: RIBF?,
    val SUGAR: SUGAR?,
    @Json(name = "SUGAR.added")
    val SUGARADDED: SUGARAdded?,
    val THIA: THIA?,
    val TOCPHA: TOCPHA?,
    val VITA_RAE: VITARAE?,
    val VITB12: VITB12?,
    val VITB6A: VITB6A?,
    val VITC: VITC?,
    val VITD: VITD?,
    val VITK1: VITK1?,
    val WATER: WATER?,
    val ZN: ZN?
)
