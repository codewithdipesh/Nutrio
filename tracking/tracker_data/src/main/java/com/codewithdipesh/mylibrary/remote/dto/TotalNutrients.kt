package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName


data class TotalNutrients(
    val CA: CA?,
    val CHOCDF: CHOCDF?,
    @SerializedName("CHOCDF.net")
    val CHOCDFNET: CHOCDFNet?,
    val CHOLE: CHOLE?,
    val ENERC_KCAL: ENERCKCAL?,
    val FAMS: FAMS?,
    val FAPU: FAPU?,
    val FASAT: FASAT?,
    val FAT: FAT?,
    val FE: FE?,
    val FIBTG: FIBTG?,
    val FOLAC: FOLAC?,
    val FOLDFE: FOLDFE?,
    val FOLFD: FOLFD?,
    val K: K?,
    val MG: MG?,
    val NA: NA?,
    val NIA: NIA?,
    val P: P?,
    val PROCNT: PROCNT?,
    val RIBF: RIBF?,
    val SUGAR: SUGAR?,
    @SerializedName("SUGAR.added")
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