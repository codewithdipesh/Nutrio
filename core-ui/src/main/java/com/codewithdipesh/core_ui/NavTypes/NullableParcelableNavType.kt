package com.codewithdipesh.core_ui.NavTypes

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType

class NullableParcelableNavType<T : Parcelable>(
    private val clazz: Class<T>
) : NavType<T?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T? {
        return null // Allow null parsing
    }

    override fun put(bundle: Bundle, key: String, value: T?) {
        bundle.putParcelable(key, value)
    }
}