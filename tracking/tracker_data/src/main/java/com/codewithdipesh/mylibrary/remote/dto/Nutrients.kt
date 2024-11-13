package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName


data class Nutrients(
    val CA: CA?,
    @SerializedName("CHOCDF")
    val carbs: CHOCDF?,
    val CHOLE: CHOLE?,
    @SerializedName("ENERC_KCAL")
    val calories: ENERCKCAL?,
    val FAMS: FAMS?,
    val FAPU: FAPU?,
    val FASAT: FASAT?,
    @SerializedName("FAT")
    val fat: FAT?,
    val FE: FE?,
    @SerializedName("FIBTG")
    val fibers: FIBTG?,
    val FOLAC: FOLAC?,
    val FOLDFE: FOLDFE?,
    val FOLFD: FOLFD?,
    val K: K?,
    val MG: MG?,
    val NA: NA?,
    val NIA: NIA?,
    val P: P?,
    @SerializedName("PROCNT")
    val protein: PROCNT?,
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
