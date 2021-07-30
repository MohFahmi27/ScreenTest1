package com.mfahmi.screentest1.utils

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun String.convertToDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
}

fun Int.isPrimeNum(): Boolean {
    if (this < 2) return false
    for (i in 2..this / 2) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}

fun String.removeSpace(): String = this.filter { !it.isWhitespace() }

fun String.isPalindromeWord(): String {
    return when(this.removeSpace().equals(this.removeSpace().reversed(), ignoreCase = false)) {
        true -> "isPalindrome"
        false -> "not Palindrome"
    }
}

@SuppressLint("NewApi")
fun getDescFromDate(dateString: String): String {
    val date = dateString.convertToDate().dayOfMonth
    return when {
        (date % 3 == 0) && (date % 2 == 0) -> "iOS"
        date % 2 == 0 -> "Blackberry"
        date % 3 == 0 -> "Android"
        else -> "Phone"
    }
}