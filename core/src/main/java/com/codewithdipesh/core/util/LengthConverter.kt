package com.codewithdipesh.core.util

import kotlin.math.roundToInt

fun inchToCm(inch: Float): Int {
    val inchRoundOf = String.format("%.1f", inch).toFloat()
    val cmPerInch = 2.54f
    return (inchRoundOf * cmPerInch).roundToInt()

}

fun ftToInch(feet: Int, inches: Int): Float {
    return (feet * 12 + inches).toFloat()
}

//fun ftToCm(ft: Float): Int {
//    val inch = ftToInch(ft)
//    return inchToCm(inch)
//}

fun InchToFt(inch: Float): Float{
    val ft = inch.toInt() / 12
    val inchPart = (inch % 12f).roundToInt()
    return "$ft.$inchPart".toFloat()
}

fun decimalToInt(decimal: Float): Int {
    val decimalStr = decimal.toString()
    return if (decimalStr.contains('.')) {
        decimalStr.split('.')[1].toInt()
    } else {
        0
    }
}