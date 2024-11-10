package com.codewithdipesh.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getStringFromDate(date : LocalDate) : String {
    if(date == LocalDate.now()) return "Today"
    else if(date == LocalDate.now().plusDays(1)) return "Tomorrow"
    else if(date == LocalDate.now().minusDays(1)) return "Yesterday"
    else return date.format(DateTimeFormatter.ofPattern("MMM dd"))
}