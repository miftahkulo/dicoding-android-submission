package com.indramahkota.data.utils

import com.indramahkota.common.utils.Constant.DATE_PARSE_ERROR
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example
 * inFormat: yyyy-MM-dd
 * outFormat: EEEE, d MMMM yyyy
 * **/
fun formatDateFromString(inputDate: String, inFormat: String, outFormat: String): String {
    val parsed: Date?
    var outputDate = ""
    val locale = Locale("en", "EN")
    val dfInput = SimpleDateFormat(inFormat, locale)
    val dfOutput = SimpleDateFormat(outFormat, locale)
    try {
        parsed = dfInput.parse(inputDate)
        if (parsed != null) {
            outputDate = dfOutput.format(parsed)
        }
    } catch (e: ParseException) {
        print(DATE_PARSE_ERROR)
    }
    return outputDate
}