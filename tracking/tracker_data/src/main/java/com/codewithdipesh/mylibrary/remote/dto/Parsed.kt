package com.codewithdipesh.mylibrary.remote.dto

data class Parsed(
    val food: String?,
    val foodId: String?,
    val foodMatch: String?,
    val measure: String?,
    val measureURI: String?,
    val nutrients: Nutrients?,
    val quantity: Double?,
    val retainedWeight: Double?,
    val status: String?,
    val weight: Double?
)