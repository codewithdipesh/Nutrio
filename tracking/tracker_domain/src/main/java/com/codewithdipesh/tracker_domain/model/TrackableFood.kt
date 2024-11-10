package com.codewithdipesh.tracker_domain.model

data class TrackableFood(
    val name : String,
    val nutrients : Map<Unit,Nutrients>,
)
