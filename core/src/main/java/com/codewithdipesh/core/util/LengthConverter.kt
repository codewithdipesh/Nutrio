package com.codewithdipesh.core.util

import kotlin.math.roundToInt

fun InchToCm(inch: Float): Int {
    val inchRoundOf = String.format("%.1f", inch).toFloat()
    val cmPerInch = 2.54f
    return (inchRoundOf * cmPerInch).roundToInt()

}
fun CmToInch(cm: Int): Float {
    return (cm.toFloat() / 2.54f)
}

fun ftToInch(feet: Int, inches: Int): Float {
    return (feet * 12 + inches).toFloat()
}


fun InchToFt(inch: Float): Float{
    val ft = inch.toInt() / 12
    val inchPart = (inch % 12f).roundToInt()
    return "$ft.$inchPart".toFloat()
}

