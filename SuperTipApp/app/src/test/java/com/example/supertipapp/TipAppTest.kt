package com.example.supertipapp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipAppTest {
    @Test
    fun calculateTipTest() {
        val tipAmount = 200.00
        val tipPerctange = 15.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(30)
        val actualTip = calculateTip(amount=tipAmount, tipPerctange=tipPerctange, false)
        assertEquals(expectedTip, actualTip)


    }

}