package com.indramahkota.app.utils

import com.indramahkota.data.utils.formatDateFromString
import junit.framework.TestCase
import org.junit.Test

class DateUtilsKtTest : TestCase() {
    @Test
    fun testFormatDateFromString() {
        val original = "2022-02-15"
        val expected = "Tue, 15 Feb 2022"
        val formatted = formatDateFromString(original, "yyyy-MM-dd", "EE, d MMM yyyy")
        assertEquals(expected, formatted)
    }
}