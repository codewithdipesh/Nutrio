package com.codewithdipesh.tracker_domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class Unit(val displayName: String) {
    TableSpoon("tablespoon"),
    TeaSpoon("teaspoon"),
    Ounce("ounce"),
    Whole("whole"),
    Gm100("gram"),
    Cup("cup");

    companion object {
        fun fromString(name: String): Unit {
            return values().find { it.displayName == name } ?: Gm100
        }
    }
}