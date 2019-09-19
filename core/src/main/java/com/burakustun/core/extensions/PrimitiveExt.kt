package com.burakustun.core.extensions

import java.security.MessageDigest
import java.text.DateFormatSymbols
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

/**
 * Created by burakustun on 2019-09-18
 */


val Boolean?.int
    get() = if (this != null && this) 1 else 0


fun Double.formatPrice(price : Double?, decimalSeperator : Char = '.', groupingSeperator : Char = ',', prefix : String = "", suffix : String = "TL") : String {
    val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.decimalSeparator = decimalSeperator
    otherSymbols.groupingSeparator = groupingSeperator

    return DecimalFormat("$prefix##$groupingSeperator##0${decimalSeperator}00 $suffix").format(price)
}

fun Float.formatPrice(price : Float?, decimalSeperator : Char = '.', groupingSeperator : Char = ',', prefix : String = "", suffix : String = "TL") : String {
    val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.decimalSeparator = decimalSeperator
    otherSymbols.groupingSeparator = groupingSeperator

    return DecimalFormat("$prefix##$groupingSeperator##0${decimalSeperator}00 $suffix").format(price)
}

fun Date.formatDate(pattern : String = "dd/MM/yyyy HH:mm" , date : Date) : String{
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}

fun String.toMD5(): String {
    // toByteArray: default is Charsets.UTF_8 - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/to-byte-array.html
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun String.toMonthName(): String {
    return DateFormatSymbols.getInstance(Locale.getDefault()).months[this.toInt()-1]
}